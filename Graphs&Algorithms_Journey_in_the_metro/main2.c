#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "Dijkstra_dans_le_metro.h"

int main (void)
{
	char * file = (char *)"graphe_test.graph";
	graphe * E = ReadGraphe( file );
	graphe * W = PCC_exo2(E, 0, 4);
	AfficheSuccesseurs(W);
	TermineGraphe(E);
	TermineGraphe(W);
	return 0;
}
