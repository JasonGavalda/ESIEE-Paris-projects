#include "graphes.h"

extern char ** text2tabseq(char *text, char fin, int *nbseq);
extern void printtabseq(int nbseq, char **tabseq);
extern void freetabseq(int nbseq, char **tabseq);
extern char * readtextfile(char * filename);
int Q3(char * u, char * v);
graphe * Q4(char ** tabseq, int n);

