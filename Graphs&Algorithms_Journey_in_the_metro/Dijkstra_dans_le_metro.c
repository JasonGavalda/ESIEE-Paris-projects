#include <stdlib.h>
#include <stdio.h>

#include "graphes.h"
#include "graphaux.h"

int chercheElement ( long  T[], long x, int n )
{
	int i=0;
	while(i<n)
	{
		if(T[i]==x)
			return 1;
		i++;
	}
	return 0;
}

void ajoutElement ( long T[], long x )
{
	int i=0;
	while(T[i]!='\0')
		i++;
	T[i]=x;
	return;
}

void retireElement ( long T[], long x )
{
	int i=0;
	while(T[i]!='\0' && T[i]!=x)
		i++;
	if (T[i]==x)
		T[i]=0;
}

void afficheTableau( long T[], int n )
{
	int i = 0;
	while(i<n)
	{
		printf("élément %d : %d ", i, T[i]);
		printf("\n");
		i++;
	}
	printf("\n");
}



void Dijkstra_exo1(graphe * g, int i)
{
	pcell p;
	int n = g->nsom;
	long S[n]; long Pi[n]; int k; int z;
	
	for(z=0; z<n; z++)
	{
		if (z!=i)
			Pi[z]= LONG_MAX;
	}
	
	S[0]=i; Pi[i]=0; k=1; long q = i;

	while(k < n && Pi[q] != LONG_MAX)
	{
		for(p = g->gamma[q]; p != NULL; p = p->next)
		{
			long y = p->som;	
			if(chercheElement(S, y, n)!=1)
				Pi[y] = min(Pi[y], Pi[q] + p->v_arc);
		}
		long x = LONG_MAX;
		for(z=0; z<n; z++)
		{
			if(chercheElement(S, z, n)!=1 && Pi[z]<x)
			{
				x = (long) Pi[z];
				q = z;
				S[k]=q;
			}
		}	
		k++;	
	}
	printf("Sommets du plus proche au plus éloigné :\n");
	afficheTableau(S,n);
	printf("Plus courtes distances à parcourir pour aller à chaque sommet :\n");
	afficheTableau(Pi,n);
}

graphe * PCC_exo2(graphe * g, int i, int a)
{
	pcell p;
	int n = g->nsom;
	long S[n]; long Pi[n]; int k; int z;

	graphe * W = InitGraphe (n, n*(n+1)/2);
	
	for(z=0; z<n; z++)
	{
		if (z!=i)
			Pi[z]= LONG_MAX;
	}
	
	S[0]=i; Pi[i]=0; k=1; long q = i; long o;

	while(k < n && Pi[q] != LONG_MAX && q!=a)
	{
		for(p = g->gamma[q]; p != NULL; p = p->next)
		{
			long y = p->som;	
			if(chercheElement(S, y, n)!=1)
				Pi[y] = min(Pi[y], Pi[q] + p->v_arc);
		}
		long x = LONG_MAX;
		o = q;
		for(z=0; z<n; z++)
		{
			if(chercheElement(S, z, n)!=1 && Pi[z]<x)
			{
				x = Pi[z];
				q = z;
				S[k]=q;
			}
		}
		if (EstSuccesseur(g, o, q)==1)
			AjouteArc( W, o, q);
		k++;
	}
	return W;
}

void Dijkstra_exo3(graphe * g, int i)
{
	pcell p;
	int n = g->nsom;
	long S[n]; long Pi[n]; int k; int z; boolean * U = EnsembleVide(n); boolean * T = EnsembleVide(n);
	
	for(z=0; z<n; z++)
	{
		if (z!=i)
		{
			Pi[z]= LONG_MAX;
			U[z] = TRUE;
			T[z] = FALSE;
		}
	}
	
	S[0]=i; Pi[i]=0; k=1; long q = i;

	while(k < n && Pi[q] != LONG_MAX)
	{
		for(p = g->gamma[q]; p != NULL; p = p->next)
		{
			long y = p->som;	
			if(U[y]==TRUE || T[y]==TRUE)
			{
				Pi[y] = min(Pi[y], Pi[q] + p->v_arc);
				U[y]=FALSE; T[y]=TRUE;
			}
		}
		long x = LONG_MAX;
		for(z=0; z<n; z++)
		{
			if(T[z]==TRUE)
			{
				if(Pi[z]<x)
				{
					x = (long) Pi[z];
					q = z;
					S[k]=q;
				}
			}
		}
		if(U[q]==FALSE && T[q]==TRUE)
			T[q]=FALSE;
		k++;	
	}
	printf("Sommets du plus proche au plus éloigné :\n");
	afficheTableau(S,n);
	printf("Plus courtes distances à parcourir pour aller à chaque sommet :\n");
	afficheTableau(Pi,n);
}
