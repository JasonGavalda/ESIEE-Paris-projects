#include <stdlib.h>
#include "graphaux.h"
#include "graphes.h"

    graphe * Sym_amelio(graphe * g)
/* ====================================================================== */
{
	graphe *g_1;
	int nsom, narc, i;
	nsom = g->nsom;
    narc = g->narc;
    g_1 = InitGraphe(nsom, narc);

	for (i = 0; i < narc; i++)
		AjouteArc(g_1, g->queue[i], g->tete[i]);

    return g_1;
}
/* ====================================================================== */
    void Bipartition(graphe * E)	{
        int i, n, k;
		pcell p;
		n = E->nsom;
		printf("Successeurs du graphe : \n");
		AfficheSuccesseurs(E);
        graphe * Es = Sym_amelio(E);
        printf("Successeurs du symetrique: \n");
        AfficheSuccesseurs(Es);
		for (i =0; i < n; i++)
            E->v_sommets[i]=0;
        i = 0;
        while(E->gamma[i]==NULL)
            i++;
        E->v_sommets[i]=1;

		for (i =0; i < n; i++)    {
            for(p = E->gamma[i]; p != NULL; p = p->next)    {
                k = p->som;
                if (E->v_sommets[i]==1 && E->v_sommets[k]!=1)
                    E->v_sommets[k]=2;
                else if (E->v_sommets[i]==2 && E->v_sommets[k]!=2)
                    E->v_sommets[k]=1;
            }
		}

		for (i =0; i < n; i++)
            Es->v_sommets[i]= 0;

        i = 0;
        while(Es->gamma[i]==NULL)
            i++;
        Es->v_sommets[i]=1;
		for (i =0; i < n; i++)    {
            for(p = Es->gamma[i]; p != NULL; p = p->next)    {
                k = p->som;
                if (Es->v_sommets[i]==1 && Es->v_sommets[k]!=1)
                    Es->v_sommets[k]=2;
                else if (Es->v_sommets[i]==2 && Es->v_sommets[k]!=2)
                    Es->v_sommets[k]=1;
            }
		}

		for (i =0; i < n; i++)  {
            if (E->v_sommets[i] == 0)   {
                E->v_sommets[i] = Es->v_sommets[i];
            }
		}
		TermineGraphe(Es);
		AfficheValeursSommets(E);
		printf("\n");
		for (i = 0; i < n; i++)  {
            if (E->v_sommets[i] == 0)   {
                printf("Ce graphe n'est pas Biparti.");
                return;
            }
		}
		printf("Ce graphe est Biparti.");
		return;
    }

    int main(void)  {
        graphe * E = GrapheAleatoire(6,6);
        Bipartition(E);
        TermineGraphe(E);
        printf("\n");
        printf("Test sur le graphe biparti de l'enonce: \n");
        graphe * F = InitGraphe(5,5);
        AjouteArc(F,0,1);
        F->tete[0] = 0;
        F->queue[0] = 1;
        AjouteArc(F,0,2);
        F->tete[1] = 0;
        F->queue[1] = 2;
        AjouteArc(F,2,3);
        F->tete[2] = 2;
        F->queue[2] = 3;
        AjouteArc(F,3,1);
        F->tete[3] = 3;
        F->queue[3] = 1;
        AjouteArc(F,4,1);
        F->tete[4] = 4;
        F->queue[4] = 1;
        Bipartition(F);
        TermineGraphe(F);
        printf("\n");
        printf("Test sur le graphe de l'exo2: \n");
        graphe * exo2 = InitGraphe(7,7);
        AjouteArc(exo2,0,1);
        exo2->tete[0] = 0;
        exo2->queue[0] = 1;
        AjouteArc(exo2,1,2);
        exo2->tete[1] = 1;
        exo2->queue[1] = 2;
        AjouteArc(exo2,2,3);
        exo2->tete[2] = 2;
        exo2->queue[2] = 3;
        AjouteArc(exo2,3,4);
        exo2->tete[3] = 3;
        exo2->queue[3] = 4;
        AjouteArc(exo2,3,5);
        exo2->tete[4] = 3;
        exo2->queue[4] = 5;
        AjouteArc(exo2,4,6);
        exo2->tete[5] = 4;
        exo2->queue[5] = 6;
        AjouteArc(exo2,5,6);
        exo2->tete[6] = 5;
        exo2->queue[6] = 6;
        Bipartition(exo2);
        TermineGraphe(exo2);
        return 0;
    }
