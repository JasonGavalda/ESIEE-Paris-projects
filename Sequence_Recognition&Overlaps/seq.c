/*
   utilitaires pour le traitement de sequences
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include "seq.h"

#include <sys/stat.h>
#include <unistd.h>

#define CARFIN '#'
#define STRSEP "#"

/* =============================================================== */
char ** text2tabseq(char *text, char fin, int *nbseq)
/* =============================================================== */
/*
   Parcourt "text" qui contient des fragments termines par le caractere fin.
   Les fragments sont ranges (sans le caractere fin) dans un tableau
     de chaines de caracteres (char **), qui est alloue et retourne
     par text2tabseq.
   En sortie, *nbseq contient le nombre de fragments trouves.
*/
{
  register int i, j, n, ts;
  char ** tabseq = NULL;
  char *p;

  /* compte le nb de sequences - ie - de caracteres fin */
  *nbseq = 0;
  ts = strlen(text);
  for (i = 0; i < ts; i++)
  {
    if (text[i] == fin)
      (*nbseq)++;
  }

  /* alloue le tableau de pointeurs tabseq */
  tabseq = (char **)malloc(*nbseq * sizeof(char *));
  if (tabseq == NULL)
  {   fprintf(stderr,"text2tabseq() : malloc failed for tabseq\n");
      exit(0);
  }

  /* transfere les chaines dans tabseq */
  j = i = 0;
  for (n = 0;
       n < *nbseq;
       n++, i = j + 1, j = i)
  {
    while (text[j] != fin) j++;
    p = (char *)malloc((j - i + 1) * sizeof(char));
    if (!p) { fprintf(stderr, "error : malloc failed\n"); exit(0); }
    strncpy(p, text+i, j-i);
    p[j-i] = '\0';
    tabseq[n] = p;
  }
  return tabseq;
} /* text2tabseq() */

/* =============================================================== */
void printtabseq(int nbseq, char **tabseq)
/* =============================================================== */
{
  int i;

  for (i = 0; i < nbseq; i++)
    printf("%s\n", tabseq[i]);
} /* printtabseq() */

/* =============================================================== */
void freetabseq(int nbseq, char **tabseq)
/* =============================================================== */
{
  int i;

  for (i = 0; i < nbseq; i++)
    if (tabseq[i] != NULL) free(tabseq[i]);
  free(tabseq);
} /* freetabseq() */

/* =============================================================== */
char * readtextfile(char * filename)
/* =============================================================== */
/*
  Lit le fichier texte de nom <filename> et retourne son contenu
    sous forme de chaine de caracteres (char *).
*/
{
  struct stat monstat;
  int N;
  char * text = NULL;
  FILE *fd = NULL;

  N = stat(filename, &monstat);
  if (N == -1)
  {
    fprintf(stderr, "error : bad file %s\n", filename);
    exit(0);
  }
  N = monstat.st_size;
  text = (char *)malloc(N+1);
  if (text == NULL)
  {   fprintf(stderr,"readtextfile() : malloc failed for text\n");
      exit(0);
  }
  fd = fopen(filename,"r");
  if (!fd)
  {
    fprintf(stderr, "readtextfile: can't open file %s\n", filename);
    exit(0);
  }

  fread(text, sizeof(char), N, fd);
  text[N] = '\0';
  fclose(fd);
  return text;
}

/* =============================================================== */
int Q3(char * u, char * v)
/* =============================================================== */

/*
    Retourne le recouvrement maximal entre
    un premier mot u et un deuxieme mot v.
*/
{
    int n = strlen(u);
    if (n > strlen(v))
        n = strlen(v);
    bool b = false;
    int i = n;
    

    while( b == false && i>0)
    {
    	char * vt = (char*) malloc(i*sizeof(char *)); //Allocation de mémoire pour la sous-chaine
    	strncpy(vt, v, i); // On copie dans la sous-chaîne les i premiers caractères de v.
    	printf("coupe 1er mot : %s\ncoupe 2nd mot : %s\n", u+(n-i), vt);
        if (strcmp(vt, u+(n-i)) == 0) // On compare la sous-chaîne aux i derniers caractères de u
        {
        	b = true;
        	printf("Correspondance trouvee !\n");
       	}
        else
        {
            i--;
            if (i==0)
            	printf("Pas de correspondance...\n");
        }
        free(vt); // On libère la mémoire pour la sous-chaîne.
    }
    return i;
}


/* =============================================================== */
graphe * Q4(char ** tabseq, int n, char * filename)
/* =============================================================== */

/*
    Crée un graphe de recouvrement des séquences du tableau
    de séquences tabseq.
*/
{
    graphe * g = InitGraphe(n, n*(n+1)/2); // Initialise le graphe.
    int rec;
    pcell p;
    g->nomsommet = (char **)malloc(n * sizeof(char *)); // Alloue la mémoire pour chaque mot dans le graphe

    for(int i = 0; i<n; i++)
    {
        g->nomsommet[i] = (char *)malloc((strlen(tabseq[i])+1) * sizeof(char)); // Alloue la mémoire pour les caractères de chaque mot dans le graphe en fonction de la taille de chaque mot de tabseq.
        strcpy(g->nomsommet[i], tabseq[i]); // Nomme chaque sommet avec un mot de tabseq.
    } 

    for(int i = 0; i<n; i++)
    {
    	g->x[i] = rand() % 300;
    	g->y[i] = rand() % 300; // Place chaque sommet sur la représentation graphique de manière aléatoire.
        for(int j = 0; j<n; j++)
        {
            if (j != i)
			{
            	rec = Q3(g->nomsommet[i], g->nomsommet[j]); // calcule le recouvrement entre deux mots, le sommet actuel i et les autres sommets j.
            	if(rec != 0)
            	{
            		AjouteArcValue(g, i, j, rec); // Si le recouvrement est non nul, on crée un arc entre les deux sommets avec comme pondération la valeur du recouvrement.
            		p = g->gamma[i];
            		printf("Recouvrement de %ld\n", p->v_arc); // Et on affiche la valeur du recouvrement.
            	}
            	// else
            	//	AjouteArc(g, i, j);
            	// Décommenter les deux lignes ci-dessus pour ajouter les arcs sans recouvrement.
            }
        }
    }
    EPSGraphe( g, filename, 3, 2, 60, 1, 0, 0, 2); // On sauvegarde dans un fichier la représentation du graphe.
    TermineGraphe(g); // On libère la mémoire utilsée pour le graphe.
    return g;
}

/* =============================================================== */
int main(int argc, char **argv)
/* =============================================================== */
{
  char * text;
  int nbseq;
  char **tabseq;
  char **test = (char **)malloc(4 * sizeof(char *));
  for(int i = 0; i<3; i++)
  	test[i] = (char *)malloc(4 * sizeof(char));
  test[0] = "TAG";
  test[1] = "CTA";
  test[2] = "ACT";

  if (argc != 2)
  {
    fprintf(stderr, "usage: %s textfile\n", argv[0]);
    exit(0);
  }
  text = readtextfile(argv[1]);
  tabseq = text2tabseq(text, CARFIN, &nbseq);
  free(text);
  Q4(test, 3, "test.eps");
  // Q4(tabseq, nbseq, "sequences.eps");
  // printtabseq(nbseq, tabseq);
  freetabseq(nbseq, tabseq);
  free(test);
}
