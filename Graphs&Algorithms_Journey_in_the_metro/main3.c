#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "Dijkstra_dans_le_metro.h"

int main (void)
{
	char * file = (char *)"graphe_test.graph";
	graphe * E = ReadGraphe( file );
	Dijkstra_exo3(E,0);
	TermineGraphe(E);	
	return 0;
}
