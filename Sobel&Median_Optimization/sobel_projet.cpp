#include<math.h>
#include <iostream>

#include <opencv2/highgui.hpp>
#include <opencv2/opencv.hpp>
#include <opencv2/imgproc/imgproc.hpp>
#include <opencv2/highgui/highgui.hpp>

#include "sobel_projet.hpp"

using namespace std;
using namespace cv;
using std::cout;

void SobelH(const Mat T, int * S, uchar * H, int i, int j)
{
    *H = T.at<uchar>(i-1, j-1)*S[0];
    *H += T.at<uchar>(i, j-1)*S[1];
    *H += T.at<uchar>(i, j+1)*S[2];
    *H += T.at<uchar>(i+1, j-1)*S[0];
    *H += T.at<uchar>(i-1, j+1);
    *H += T.at<uchar>(i+1, j+1);
    *H = abs(*H);
}

void SobelV(const Mat T, int *S, uchar * V, int i, int j)
{
    *V = T.at<uchar>(i+1, j-1)*S[0];
    *V += T.at<uchar>(i-1, j)*S[2];
    *V += T.at<uchar>(i+1, j)*S[1];
    *V += T.at<uchar>(i+1, j+1)*S[0];
    *V += T.at<uchar>(i-1, j-1);
    *V += T.at<uchar>(i-1, j+1);
    *V = abs(*V);
}


void sobelperso (const Mat T , Mat G )
{
    uchar H, V;
    H=0;
    V=0;
    int  sobel[3] = {-1,-2,2};
    
    // ----------- Traitement de l'image sans ses bords --------------
    for (int i = 1; i<T.rows-1; i++)
    {
         for (int j = 1; j<T.cols-1; j++)
        {
            SobelH(T, sobel, &H, i, j);            
            SobelV(T, sobel, &V, i, j);
            if (H < 225 && V < 225)
            	G.at<uchar>(i,j)= (H+V)/2;
        }
    }

	// ------- Traitement des effets de bord -------------

    for(int i =  1; i < T.rows -1;i++)
    {
        G.at<uchar>(i,0) = G.at<uchar>(i,1);
        G.at<uchar>(i,G.cols-1) = G.at<uchar>(i,G.cols-2);
    }

    for(int i =  1; i < T.cols -1;i++)
    {
        G.at<uchar>(0,i) = G.at<uchar>(1,i);
        G.at<uchar>(G.rows-1,i) = G.at<uchar>(G.rows-2,i);
    }

    G.at<uchar>(0,0) = G.at<uchar>(1,1);
    G.at<uchar>(0,G.cols -1) = G.at<uchar>(1,G.cols - 2);
    G.at<uchar>(G.rows-1,0) = G.at<uchar>(G.rows-2,1);
    G.at<uchar>(G.rows-1, G.cols -1) = G.at<uchar>(G.rows - 2, G.cols - 2);
}
