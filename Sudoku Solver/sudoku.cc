#include <fstream>
#include <vector>
#include <math.h>
#include "ortools/linear_solver/linear_solver.h"

using namespace std;

namespace operations_research {
	void sudokoSolver(int n, vector<vector<int>> & puzzle) {
		// Create the mip solver with the CBC backend.
		MPSolver solver("sudokoSolver",
			MPSolver::CBC_MIXED_INTEGER_PROGRAMMING);

		const double infinity = solver.infinity();

		int N = 4;

		//array of N variables
		vector<MPVariable *> Variables(N);

		//each variable is an integer between 0 and 10
		for (int i = 0; i < N; i++)
			Variables[i] = solver.MakeIntVar(0.0, 10.0, "");

		//constraint: x_1 + 2x_2 + 4x_3 + 2x_4 = 20
		{
			MPConstraint* c = solver.MakeRowConstraint(20.0, 20.0);
			c->SetCoefficient(Variables[0], 1);
			c->SetCoefficient(Variables[1], 2);
			c->SetCoefficient(Variables[2], 4);
			c->SetCoefficient(Variables[3], 2);
		}

		//constraint: 10 <= x_1 - 3x_2 + x_3 - x_4 <= infinity
		{
			MPConstraint* c = solver.MakeRowConstraint(10, infinity);
			c->SetCoefficient(Variables[0], 1);
			c->SetCoefficient(Variables[1], -3);
			c->SetCoefficient(Variables[2], 1);
			c->SetCoefficient(Variables[3], -1);
		}

		//objective function: maximize 2x_1 + 3x_2 + 4x_3 + 2x_4
		{
			MPObjective *obj = solver.MutableObjective();
			obj->SetCoefficient(Variables[0], 2);
			obj->SetCoefficient(Variables[1], 3);
			obj->SetCoefficient(Variables[2], 4);
			obj->SetCoefficient(Variables[3], 2);
			obj->SetMaximization();
		}

		LOG(INFO) << "Number of variables = " << solver.NumVariables();
		LOG(INFO) << "Number of constraints = " << solver.NumConstraints();

		const MPSolver::ResultStatus result_status = solver.Solve();
		if (result_status != MPSolver::OPTIMAL) {
			LOG(FATAL) << "The problem does not have an optimal solution!";
		}

		LOG(INFO) << "Solution \t";
		for (int i=0;i<N;i++) LOG(INFO) << "x_" << i+1 << ": " <<  Variables[i]->solution_value() << " ";
	}
	
	void superSudokuSolver (int n, vector<vector<int>> & puzzle)
	{
		// Create the mip solver with the CBC backend.
		MPSolver solver("superSudokuSolver",
			MPSolver::CBC_MIXED_INTEGER_PROGRAMMING);

		const double infinity = solver.infinity();

		int N = 9;

		vector<vector<vector<MPVariable *>>> Values(N);
		
		// Initialisation des variables.
		for (int i = 0; i < N; i++)
		{
			Values[i] = vector<vector<MPVariable *>>(N);
			for (int j = 0; j < N; j++)
			{
				Values[i][j] = vector<MPVariable *>(N);
				
				for (int k = 0; k < N; k++)
				{
					Values[i][j][k] = solver.MakeIntVar(0.0, 1.0, "");
				}
			}
		}
		
		// Contrainte : Les nombres déjà rentrés imposent la valeur 1 pour la variable correspondante.
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				if (puzzle[i][j] != 0)
				{
					{
						MPConstraint* c = solver.MakeRowConstraint(1.0, 1.0);
						int k = puzzle[i][j];
						c->SetCoefficient(Values[i][j][k-1], 1);
					}
				}
			}
		}
		
		// Contrainte : Chaque cellule ne contient qu'une seule variable à 1.
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				{
					MPConstraint* c = solver.MakeRowConstraint(1.0, 1.0);
					for (int k = 0; k < N; k++)
					{
						c->SetCoefficient(Values[i][j][k], 1);
					}
				}
			}
		}
		
		// Contrainte de ligne : une valeur n'apparaît qu'une seule fois par ligne.
		for (int i = 0; i < N; i++)
		{
			{
				for (int k = 0; k < N; k++)
				{
					{
						MPConstraint* c = solver.MakeRowConstraint(1.0, 1.0);
						for (int j = 0; j < N; j++)
						{
							c->SetCoefficient(Values[i][j][k], 1);
						}
					}
				}
			}
		}
		
		// Contrainte de colonne : une valeur n'apparaît qu'une seule fois par colonne.
		for (int j = 0; j < N; j++)
		{
			{
				for (int k = 0; k < N; k++)
				{
					{
						MPConstraint* c = solver.MakeRowConstraint(1.0, 1.0);
						for (int i = 0; i < N; i++)
						{
							c->SetCoefficient(Values[i][j][k], 1);
						}
					}
				}
			}
		}
		
		// Contrainte de carré : une valeur n'apparaît qu'une seule fois par carré.
		for (int x = 0; x < sqrt(N); x++)
		{
			for (int y = 0; y < sqrt(N); y++)
			{
				{
					for (int k = 0; k < N; k++)
					{
						MPConstraint* c = solver.MakeRowConstraint(1.0, 1.0);
						for (int i = x*sqrt(N); i < (x+1)*sqrt(N); i++)
						{
							for (int j = y*sqrt(N); j < (y+1)*sqrt(N); j++)
								c->SetCoefficient(Values[i][j][k], 1);
						}
					}
				}
			}
		}
		
		{
			MPObjective *obj = solver.MutableObjective();
			obj->SetCoefficient(Values[0][0][0], 1);
			obj->SetMinimization();
		}
		
		cout << " Sudoku : " << endl;
		for (int i=0; i < N; i++)
		{
			for (int j=0; j < N; j++)
			{
				cout << puzzle[i][j] << " ";
			}
			cout << endl;
		}
		
		LOG(INFO) << "Number of variables = " << solver.NumVariables();
		LOG(INFO) << "Number of constraints = " << solver.NumConstraints();

		const MPSolver::ResultStatus result_status = solver.Solve();
		if (result_status != MPSolver::OPTIMAL) {
			LOG(FATAL) << "The problem does not have an optimal solution!";
		}
		LOG(INFO) << "Solution :\t";
		/*
		for (int i=0; i < N; i++)
		{
			for (int j=0; j < N; j++)
			{
				for (int k=0; k < N; k++)
				{
					if (Values[i][j][k]->solution_value() == 1)
					{
						LOG(INFO) << "x_" << i+1 << j+1 << ": " << k+1 << " ";
					}
				}
			}
		}
		*/
		
		for (int i=0; i < N; i++)
		{
			for (int j=0; j < N; j++)
			{
				for (int k=0; k < N; k++)
				{
					if (Values[i][j][k]->solution_value() == 1)
					{
						cout << k+1 << " ";
					}
				}
			}
			cout << endl;
		}
		
	}
}   

int main(int argc, char** argv) {
	ifstream fin(argv[1]);
	if (!fin.is_open()) return 0;

	int n;
	fin >> n;

	int N = n * n;
	vector<vector<int>> input(N);

	for (int i = 0; i < N; i++)
	{
		input[i] = vector<int>(N);
		for (int j = 0; j < N; j++)
			fin >> input[i][j];
	}


	// operations_research::sudokoSolver(n, input);
	operations_research::superSudokuSolver(n, input);
	return EXIT_SUCCESS;
}
