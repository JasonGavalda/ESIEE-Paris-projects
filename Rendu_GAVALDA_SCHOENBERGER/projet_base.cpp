/*
 * Fichier source pour le projet d'unité
 *  INF-4101C
 *---------------------------------------------------------------------------------------------------
 * Pour compiler : g++ `pkg-config --cflags opencv4` median.cpp sobel_projet.cpp sobel_projet_opti.cpp projet_base.cpp `pkg-config --libs opencv4` -o projet
 *---------------------------------------------------------------------------------------------------
 * auteur : Eva Dokladalova 09/2015
 * modification : Jason Gavalda, Maxime Schoenberger 10/2020


/*
 * Libraries stantards
 *
 */
#include <stdio.h>
#include <stdlib.h>

/*
 * Libraries OpenCV "obligatoires"
 *
 */
#include <opencv2/highgui.hpp>
#include <opencv2/opencv.hpp>
#include <opencv2/imgproc/imgproc.hpp>
#include <opencv2/highgui/highgui.hpp>

/*
 * Ajout des programmes du projet
 *
 */
#include "median.hpp"
#include "sobel_projet.hpp"
#include "sobel_projet_opti.hpp"

/*
 * Définition des "namespace" pour Ã©vite cv::, std::, ou autre
 *
 */
using namespace std;
using namespace cv;
using std::cout;

/*
 * Some usefull defines
 * (comment if not used)
 */
#define PROFILE
#define VAR_KERNEL
#define N_ITER 100

#ifdef PROFILE
#include <time.h>
#include <sys/time.h>
#endif


/*
 *
 *--------------- MAIN FUNCTION ---------------
 *
 */
int main () {
//----------------------------------------------
// Video acquisition - opening
//----------------------------------------------
  VideoCapture cap(0); // le numéro 0 indique le point d'accès à  la caméra 0 => /dev/video0
  if(!cap.isOpened()){
    cout << "Errore"; return -1;
  }

//----------------------------------------------
// Déclaration des variables - imagesize
// Mat - structure contenant l'image 2D niveau de gris
// Mat3b - structure contenant l'image 2D en couleur (trois cannaux)
//
  Mat3b frame; // couleur
  Mat frame1; // niveau de gris
  Mat frame_gray; // niveau de gris
  Mat grad_x;
  Mat grad_y;
  Mat abs_grad_y;
  Mat abs_grad_x;
  Mat median_mat_perso;
  Mat grad;
  Mat grad_perso;

// variable contenant les paramètres des images ou d'éxécution
  int ddepth = CV_16S;
  int scale = 1;
  int delta = 0;
  unsigned char key = '0';

 #define PROFILE

#ifdef PROFILE
// profiling / instrumentation libraries
#include <time.h>
#include <sys/time.h>
#endif

//----------------------------------------------------
// Création des fenêtres pour affichage des résultats
// vous pouvez ne pas les utiliser ou ajouter selon ces exemple
//
  cv::namedWindow("Video input", WINDOW_AUTOSIZE);
  cv::namedWindow("Video gray levels", WINDOW_AUTOSIZE);
  cv::namedWindow("Video Mediane", WINDOW_AUTOSIZE);
  cv::namedWindow("Video Mediane_perso", WINDOW_AUTOSIZE);
  cv::namedWindow("Video Edge detection", WINDOW_AUTOSIZE);
  cv::namedWindow("Video Edge detection_perso", WINDOW_AUTOSIZE);
// placement arbitraire des  fenêtre sur écran
// sinon les fenêtres sont superposée l'une sur l'autre
  cv::moveWindow("Video input", 10, 30);
  cv::moveWindow("Video gray levels", 800, 30);
  cv::moveWindow("Video Mediane", 10, 500);
  cv::moveWindow("Video Mediane_perso", 10, 500);
  cv::moveWindow("Video Edge detection", 700, 500);
  cv::moveWindow("Video Edge detection_perso", 1400, 500);


// --------------------------------------------------
// boucle infinie pour traiter la séquence vidéo
//
  while(key!='q'){
  //
  // acquisition d'une trame video - librairie OpenCV
    cap.read(frame);
  //conversion en niveau de gris - librairie OpenCV
    cvtColor(frame, frame_gray, cv::COLOR_BGR2GRAY);


   // image smoothing by median blur
   //
 int n = 17;
 int k = 1;
 //#ifdef PROFILE
 //struct timeval start, end;
 //for (k;k<n;k+=2)
//{
// gettimeofday(&start, NULL);
// #endif
     medianBlur(frame_gray, frame1, 3);
// #ifdef PROFILE
 //gettimeofday(&end, NULL);
 //double e = ((double) end.tv_sec * 1000000.0 + (double) end.tv_usec);
 //double s = ((double) start.tv_sec * 1000000.0 + (double) start.tv_usec);
 //printf("%d : %lf\n", k, (e - s));
//}
// #endif

	// ------------------------------------------------
	#ifdef PROFILE
 	struct timeval start0, end0;
	gettimeofday(&start0, NULL);
	#endif 
    
    // calcul du gradient- librairie OpenCV
    /// Gradient Y
    Sobel( frame1, grad_x, ddepth, 1, 0, 3, scale, delta, BORDER_DEFAULT );
	/// absolute value
    convertScaleAbs( grad_x, abs_grad_x );
    /// Gradient Y
    Sobel( frame1, grad_y, ddepth, 0, 1, 3, scale, delta, BORDER_DEFAULT );
	/// absolute value
    convertScaleAbs( grad_y, abs_grad_y );
    /// Total Gradient (approximate)
    addWeighted( abs_grad_x, 0.5, abs_grad_y, 0.5, 0, grad );
    												
 	#ifdef PROFILE
 	gettimeofday(&end0, NULL);
 	double e0 = ((double) end0.tv_sec * 1000000.0 + (double) end0.tv_usec);
 	double s0 = ((double) start0.tv_sec * 1000000.0 + (double) start0.tv_usec);
 	printf("sobel opencv: %lf\n", (e0 - s0));
 	#endif
    
    grad_perso = grad.clone();
    median_mat_perso = frame1.clone();
    for (int i = 0; i<grad_perso.rows; i++)
    {
    	for (int j = 0; j<grad_perso.cols; j++)
    	{
    		median_mat_perso.at<uchar>(i,j)= 0;
    		grad_perso.at<uchar>(i,j)= 0;
    	}
    }

		// -------------------------------------------------
	#ifdef PROFILE
 	struct timeval start1, end1;
	gettimeofday(&start1, NULL);
	#endif 
    computeMedianMatOpti(frame_gray, median_mat_perso); //Ajouter "Opti" au nom de la fonction pour utiliser la fonction optimisée.
 	#ifdef PROFILE
 	gettimeofday(&end1, NULL);
 	double e1 = ((double) end1.tv_sec * 1000000.0 + (double) end1.tv_usec);
 	double s1 = ((double) start1.tv_sec * 1000000.0 + (double) start1.tv_usec);
 	printf("median perso: %lf\n", (e1 - s1));
 	#endif
	
    	// -------------------------------------------------
    	// calcul du gradient- Sobel perso
    	#ifdef PROFILE
 	struct timeval start2, end2;
	gettimeofday(&start2, NULL);
	#endif 
    	sobel_perso_opti( median_mat_perso, grad_perso ); 	// Remplacer par le nom de la fonction par "sobelperso" pour tester l'algorithme naïf.											
 	#ifdef PROFILE
 	gettimeofday(&end2, NULL);
 	double e2 = ((double) end2.tv_sec * 1000000.0 + (double) end2.tv_usec);
 	double s2 = ((double) start2.tv_sec * 1000000.0 + (double) start2.tv_usec);
 	printf("sobel perso opti: %lf\n", (e2 - s2));
 	#endif
    
    	// -------------------------------------------------
	// visualisation
	// taille d'image réduite pour meilleure disposition sur écran
    //    resize(frame, frame, Size(), 0.5, 0.5);
    //    resize(frame_gray, frame_gray, Size(), 0.5, 0.5);
    //    resize(grad, grad, Size(), 0.5, 0.5);
    imshow("Video input",frame);
    imshow("Video gray levels",frame_gray);
    imshow("Video Mediane",frame1);
    imshow("Video Mediane_perso",median_mat_perso);
    imshow("Video Edge detection",grad);
    imshow("Video Edge detection_perso", grad_perso);


    key=waitKey(5);
  }
}
