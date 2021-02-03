#include<stdio.h>
#include<stdlib.h>
#include <sys/stat.h>
#include <string.h>

struct alignement
{
  char * x;
  char * y;
};


/* =============================================================== */
char * readtextfile(char * filename)
  /* Retourne le contenu du fichier texte filename */
/* =============================================================== */
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
  if((N>0) && (text[N-1] == '\n') ) text[N-1] = '\0';
  else text[N-1] = '\0';
  fclose(fd);
  return text;
}

/* =============================================================== */
int Imax(int a, int b)
/* Retourne  le maximum de a et b                                  */
/* =============================================================== */
{
  if (a < b) return b;
  else return a;	       
}

/* =============================================================== */
int Imin2(int a, int b)
/* Retourne  le minimum de a et b                                  */
/* =============================================================== */
{
  if (a < b) return a;
  else return b;	       
}

/* =============================================================== */
int Imin3(int a, int b, int c)
/* Retourne  le minimum de a, b et c                               */
/* =============================================================== */
{
  return Imin2(Imin2(a,b),c);
}

/* =============================================================== */
void retourne(char *c)
/* Retourner la chaîne de caractère c                              */
/* =============================================================== */
{
  char tmp;
  int m, j, i;
  m = strlen(c);
  j = m/2;
  for(i = 0; i < j; i++ ){
    tmp = c[i];
    c[i] = c[m-i-1];
    c[m-i-1] = tmp;
  }
}
/* =============================================================== */
void afficheSeparateurHorizontal(int nbcar)
/* =============================================================== */
{
  int i;
  printf("|-");
  for(i=0; i < nbcar; i++)
    printf("-");
  printf("-|-");
  for(i=0; i < nbcar; i++)
    printf("-");
  printf("-|\n");
}


/* =============================================================== */
void affiche(char* texte1, char* texte2, int nbcar)
  /* Affiche simultanément texte1 et texte 2 en positionnnant nbcar  
     caractères sur chaque ligne. */
/* =============================================================== */
{
  int i, l1, l2, l;
  
  char *t1,*t2;
  
  char out[512];
  
  l1 = strlen(texte1);
  l2 = strlen(texte2);

  t1 = (char*) malloc(sizeof(char) * (nbcar + 1));
  t2 = (char*)malloc(sizeof(char) * (nbcar + 1));

  l = Imax(l1, l2);
  afficheSeparateurHorizontal(nbcar);
  for(i = 0; i < l; i+= nbcar){
    if (i < l1) {
      strncpy(t1, &(texte1[i]), nbcar);
      t1[nbcar] = '\0';
    } else t1[0] = '\0';
    if (i < l2) {
      strncpy(t2, &(texte2[i]),nbcar);
      t2[nbcar] = '\0';
    } else t2[0] = '\0';
    
    sprintf(out, "| %c-%ds | %c-%ds |\n",'%', nbcar, '%', nbcar);
    printf(out, t1,t2);
  }
  afficheSeparateurHorizontal(nbcar);
  free(t1);
  free(t2);
}



/* =============================================================== */
void affiche2(char* texte1, char* texte2, int nbcar)
  /* idem affiche, mais avec un formattage différent
/* =============================================================== */
{

  int i, l1, l2, l;
  
  char *t1,*t2;
  
  char out[512];
  
  l1 = strlen(texte1);
  l2 = strlen(texte2);

  t1 = (char*) malloc(sizeof(char) * (nbcar + 1));
  t2 = (char*)malloc(sizeof(char) * (nbcar + 1));

  l = Imax(l1, l2);

  for(i = 0; i < l; i+= nbcar){
    if (i < l1) {
      strncpy(t1, &(texte1[i]), nbcar);
      t1[nbcar] = '\0';
    } else t1[0] = '\0';
    if (i < l2) {
      strncpy(t2, &(texte2[i]),nbcar);
      t2[nbcar] = '\0';
    } else t2[0] = '\0';
    
    sprintf(out, "x: %c-%ds \ny: %c-%ds\n",'%', nbcar, '%', nbcar);
    printf(out, t1,t2);

  }
  free(t1);
  free(t2);
}

int Ins (char * x, char * y, int pos)
{
    return 1;
}

int Del (char * x, int pos)
{
    return 1;
}

int Sub (char * x, char * y, int i, int j)
{
    if (x[i] == y[j])
        return 0;
    else
        return 1;
}

int EditDistance (char * x, char * y, int m, int n, int ** T)
{
    T[0][0] = 0;
    /*========= Remplissage des bords gauche et haut de la matrice ===========*/
    for (int i = 1; i <= m; i++)
    {
        T[i][0] = T[i-1][0] + Del(x, i-1);
    }
    for (int j = 1; j <= n; j++)
    {
        T[0][j] = T[0][j-1] + Ins(x, y, j-1);
    }
    /*========================================================================*/
    int min;
    /*========== Remplissage du reste de la matrice ==========================*/
    for (int i = 1; i <= m; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            min = T[i][j-1] + Ins(x, y, j-1);

            if ((T[i-1][j] + Del(x, i-1)) <= min)
                min  = T[i-1][j] + Del(x, i-1);

            if ((T[i-1][j-1] + Sub(x, y, i-1, j-1)) <= min)
                min = T[i-1][j-1] + Sub(x, y, i-1, j-1);
			// Le test précédent étant placé en dernier avec une inégalité large, il est choisi par défaut en cas d'égalité.
            T[i][j] = min;
        }
    }
    return T[m][n];
}

/* =========================================================================== */
void Alignement (int ** T, char * x, char * y, char * xp, char * yp, int m, int n)
/* Remplit xp et yp, l'alignement optimal entre les chaînes x et y.
/*============================================================================ */
{
	int i = m;
	int j = n;
	int k = 0;
	int min;

	while (i > 0 && j > 0) 	{
		/*========== Copie à chaque itération de xp et yp pour pouvoir concaténer par le début de la chaine.*/
		char * xp_copy = (char *)malloc(2*m * sizeof(char));
		strcpy(xp_copy, xp);
		char * yp_copy = (char *)malloc(2*n * sizeof(char));
		strcpy(yp_copy, yp);
		/*=========================================================================*/
		
		/*========== Test parmi les 3 nombres précédents dans le tableau pour trouver le plus petit.*/
		if (T[i][j-1] <= T[i][j])
            {min = T[i][j-1]; k = 1;}
		if(T[i-1][j] <= min)
            {min = T[i-1][j]; k = 2;}
		if(T[i-1][j-1] <= min)
            {k = 3;}
		/*=========== En cas d'égalité, c'est le k=3 qui sera sélectionné (la diagonale) =*/
		if (k==1)
		{	
			j--; 
			sprintf(xp, "_%s", xp_copy);
			sprintf(yp, "%c%s", y[j], yp_copy);
		} // Ajout de blanc dans xp, équivalent à une insertion.
		else if (k==2) 
		{
			i--; 
			sprintf(yp, "_%s", yp_copy);
			sprintf(xp, "%c%s", x[i], xp_copy);
		} // Ajout de blanc dans yp, équivalent à une suppression.
		else if (k==3) 
		{
			i--; 
			j--; 
			sprintf(xp, "%c%s", x[i], xp_copy);
			sprintf(yp, "%c%s", y[j], yp_copy);
		} // Equivalent de la substitution, on recopie les caractères sans ajout de blanc.
		free(xp_copy);
		free(yp_copy);
	}

	/* Traitement des bords de la matrice, c'est à dire quand on a traité un des deux mots entièrement.*/
	if ( i == 0)	{
		while (j != 0)
        {
        	char * xp_copy = (char *)malloc(m * sizeof(char));
			strcpy(xp_copy, xp);
			char * yp_copy = (char *)malloc(n * sizeof(char));
			strcpy(yp_copy, yp);	
            j--;
            sprintf(xp, "_%s", xp_copy);
            sprintf(yp, "%c%s", y[j], yp_copy);
			free(xp_copy);
			free(yp_copy);
        }

    }

	else if ( j == 0)	{
		while (i != 0)
        {
        	char * xp_copy = (char *)malloc(m * sizeof(char));
			strcpy(xp_copy, xp);
			char * yp_copy = (char *)malloc(n * sizeof(char));
			strcpy(yp_copy, yp);	
            i--;
            sprintf(yp, "_%s", yp_copy);
            sprintf(xp, "%c%s", x[i], xp_copy);
			free(xp_copy);
			free(yp_copy);
        }
    }
    /* ===================================================== */
}

/* =============================================================== */
int main(int argc, char **argv)
/* =============================================================== */
{
  char *x, *y; 
  
  if(argc != 3){
    printf("usage: %s text1 text2\n", argv[0]);
    exit(0);
  }  

  x = readtextfile(argv[1]);
  y = readtextfile(argv[2]);;

  affiche(x, y, 50);
  int m = strlen(x);
  int n = strlen(y);
  int ** T;
  T = (int**) malloc((m+1)*sizeof(int*));
  for (int i = 0; i<(m+1); i++)
     T[i] = (int*) malloc((n+1)*sizeof(int));
  int result;
  result = EditDistance(x, y, m, n, T);
  printf("result : %d\n", result);
  for (int i = 0; i<=m; i++)
  {
      for (int j = 0; j<=n; j++)
      {
          // printf("%d", T[i][j]);
      }
      // printf("\n");
  }
  char * xp = (char *)malloc(2*m * sizeof(char));
  char * yp = (char *)malloc(2*n * sizeof(char));
  Alignement(T, x, y, xp, yp, m, n);
  printf("Resultat final :\n");
  affiche(xp, yp, 50);
  affiche2(xp, yp, 100);
  free(T);
  free(xp);
  free(yp);
  
  free(x);
  free(y);
  
}
