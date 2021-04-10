/*******************************************************************/ 
/*  HPC(3) : TP4 parallélisation de la solution numérique des Equations
 * aux dérivées partielles, l'équation de la diffusion de la chaleur
 *  
 *  Eva Dokladalova, 2019
 */
#include <stdio.h>
#include <sys/types.h>
#include <stdlib.h>
#include <stdint.h>
#include <string.h>
#include <omp.h>
#include "function.h"

//-----------------------------------------------------------
// MAIN FUNCTION
//-----------------------------------------------------------
int main (int argc, char *argv[])
{
 
  // noms des fichiers d'entrée et de sortie
  char *filename=argv[1];
  char *file_out=argv[2];

  // pointeurs vers les matrices pour l'intégration temporelle
  float *T;
  float *Tdt;
  float *swap;

  // dimension de matrice, valeur maximale
  int v;  // max  value in matrix
  int rw; // row size
  int cl; // column size

  // vérification des arguments d'entrée
  if (argc != 3)
    {  fprintf(stderr,"Input parameters missing:\n./program_name <inpout.pgm> <output.pgm>\n");
      return(0);
    }

  
  //-------------------------------------------------------------
  // OPEN DATA FILE AND ALLOCATE INPUT IMAGE MEMORY (float precision)
  //-------------------------------------------------------------
  T = readimg(filename, &rw, &cl, &v);
  Tdt = (float *) calloc (rw*cl,sizeof(float));
 
  //-------------------------------------------------------------
  // PUT HERE THE NUMERICAL SOLUTION OF HEAT EQUATION
  // complete variables necessary for for the numerical scheme computing
  //-------------------------------------------------------------
  int i;
  int j;
  int it;
  float dt = 0.001;
  
  float nn,sn,en,wn, cp;
  float d2x, d2y;
  float h = 1;
  float t = 1;
  float F;
  
  int N = (int) (t/dt); // here we compute the number of iterations
  memcpy(Tdt,T,sizeof(float)*rw*cl);
  float t0 = omp_get_wtime();     

  // A COMPLETER SELON LE DERNIER COURS :-)
  

  for (int k = 0; k<N; k++) // Itérations temporelles 
  {
	  // omp_set_num_threads(1);
	  // #pragma omp parallel for collapse(2) private(cp, F, d2x, d2y, nn, sn, en, wn)	  
      for (int i = 0; i < rw ; i++) // Itérations spatiales sur les lignes.
	  { 
	  	for (int j = 0; j < cl ; j++) // Itérations spatiales sur les colonnes.
	    {
	      cp = T [j + i*rw];
	      
	      if (i == 0) // Dans le cas où on est sur un bord supérieur.
			nn = cp;
	      else
			nn = T[j + (i-1)*rw];

	      if (i == (rw-1)) // Dans le cas où on est sur un bord inférieur
			sn = cp;
	      else
			sn = T[j + (i+1)*rw];
	  
	      if (j == 0) // Dans le cas où on est sur un bord gauche.
			wn = cp;
	      else
			wn = T[(j-1) + i*rw];

	      if (j == (cl-1)) // Dans le cas où on est sur un bord droit.
			en = cp;
	      else
			en = T[(j+1) + i*rw];
	  
	      d2x = (en - 2*cp + wn)/(h*h); // Calcul de la composante horizontale.
	      d2y = (nn - 2*cp + sn)/(h*h); // Calcul de la composante verticale.

	      F = d2x + d2y; // Combinaison.

	      Tdt[j + i*rw] = cp + dt*F; // Calcul de la valeur de l'equation de diffusion.  
	    }
	  }
      
      swap = Tdt;
      Tdt = T;
      T = swap;
    }
   
  float t1 = omp_get_wtime();
  float temps_reel=t1-t0;
  printf( " temps  reel pour 2D %lf : \n", temps_reel);
  
  //-------------------------------------------------------------
  // WRITE RESULT IN A PGN IMAGE 
  //-------------------------------------------------------------
   writeimg(file_out, Tdt, rw, cl, v);
   free(Tdt);
   free(T);
   return(0);
}

