#include <stdio.h>
#include <stdlib.h>
#include <string.h>

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
		printf("xp : %s\n", xp);
		printf("yp : %s\n", yp);
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
			printf("xp : %s\n", xp);
			printf("yp : %s\n", yp);
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
			printf("xp : %s\n", xp);
			printf("yp : %s\n", yp);
        }
    }
}

int main()
{
    char * x = "NICHE";
    char * y = "CHIEN";
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
            printf("%d", T[i][j]);
        }
        printf("\n");
    }
    char * xp = (char *)malloc(2*m * sizeof(char));
    char * yp = (char *)malloc(2*n * sizeof(char));
    Alignement(T, x, y, xp, yp, m, n);
    printf("Resultat final :\n");
    printf("%s\n", xp);
    printf("%s\n", yp);
    free(T);
    free(xp);
    free(yp);
    return 0;
}
