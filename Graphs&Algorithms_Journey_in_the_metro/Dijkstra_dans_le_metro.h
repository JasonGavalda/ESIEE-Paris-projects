/*! \file Dijkstra_dans_le_metro.h
\brief Exercice 1
*/
#include <stdio.h>
#include <stdlib.h>

#include "graphes.h"
#include "graphaux.h"

int chercheElement (long T[], long x);
void ajoutElement (long T[], long x);
void retireElement (long T[], long x);
void afficheTableau (long T[]);

void Dijkstra_exo1(graphe * g, int i);
graphe * PCC_exo2(graphe * g, int i, int a);
void Dijkstra_exo3(graphe * g, int i);
