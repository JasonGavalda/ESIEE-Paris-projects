#include <stdio.h>
#include <sys/types.h>
#include <stdlib.h>
#include <stdint.h>
#include <string.h>
#include <omp.h>
#include <errno.h>
#include <unistd.h>
#include <sys/stat.h>
#include "function.h"

int main (int argc, char *argv[])
{
 
  // noms des fichiers d'entrée et de sortie
  char *filename=argv[1];
  char *file_out=argv[2];
  
  float *im; // input data
  char *rd;
  float c,v;
  struct stat st;
  stat(filename, &st);
  int size = st.st_size / 7; // Nécessité de diviser par 7 à cause du caractère de saut de ligne.
  char buffer[size];
  FILE *fd = NULL;
  FILE *fd_out = NULL;
  
  // open input file 
  fd = fopen(filename,"r");
  if (fd==NULL)
    {
      fprintf(stderr, "Input image reading failed)\n");
      return (0);
    }
 
  
  
  //data allocation and initialization
  	im = (float *) calloc (size,sizeof(float));
 	  if (im==NULL)
    {
      fprintf(stderr, "Data allocation (failed %d) bytes)\n", size);
      return (0);
    }
  
  for(int u = 0; u<size; u++)	{
  	fscanf(fd, "%f", &c);
  	im[u]=(float) c;
  }
    
  	
  int i;
    // close file and return pointer to the iamge data
  fclose(fd);
  // pointeurs vers les matrices pour l'intégration temporelle
  float *Tdt;
  float *swap;

  // dimension de matrice, valeur maximale
  int mv;  // max  value in matrix

  // vérification des arguments d'entrée
  if (argc != 3)
    {  fprintf(stderr,"Input parameters missing:\n./program_name <inpout.txt> <output.txt>\n");
      return(0);
    }
    
  Tdt = (float *) calloc (size,sizeof(float));
  
  int j;
  int it;
  int N;
  float dt = 0.001;
  
  float nn,sn,en,wn, cp;
  float d2x, d2y;
  float h = 1;
  float t = 1;
  float F;
  
  float l1 = im[0];
  float l2 = im[size];
  
  memcpy(Tdt,im,sizeof(float)*size);
  float t0 = omp_get_wtime();     

  // A COMPLETER SELON LE DERNIER COURS :-)
  
  N = (int) (t/dt); // here we compute the number of iterations
  for (int k = 0; k<N; k++) // Chaque itération correspond à une itération de temps.
  {
    omp_set_num_threads(1); // A commenter/décommenter pour utiliser le parallélisme.
     #pragma omp parallel for // idem. Parallélise les boucles sur le nombre de threads défini.
  	for (i=0; i < size ; i++)	{ // Chaque itération correspond à une itération d'espace.
  		if (i==0)
  			Tdt[i] = im[i] + dt*(im[i+1]-l1)/2; // gestion des bords.
  		else if (i==size)
  			Tdt[i] = im[i] + dt*(l2-im[i-1])/2; // gestion des bords.
  		else
  			Tdt[i] = im[i] + dt*(im[i+1]-im[i-1])/2;
  	}
  
  swap = Tdt;
  Tdt = im;
  im = swap;
  }
  
  float t1 = omp_get_wtime();
  float temps_reel=t1-t0;
  printf( " temps  reel pour 1D : %lf \n", temps_reel);
  
  fd_out = fopen(file_out,"w");
  if (!fd_out)
    {
      fprintf(stderr, "%s: cannot open file\n", filename);
      exit(0);
    } 
  for (i = 0; i < size; i++)
    { 
      fprintf(fd_out, "%f \n", im[i]);
    }      
  fclose(fd_out);
  free(Tdt);
  free(im);
  return(0);
}
