#include<math.h>
#include <iostream>

#include <opencv2/highgui.hpp>
#include <opencv2/opencv.hpp>
#include <opencv2/imgproc/imgproc.hpp>
#include <opencv2/highgui/highgui.hpp>

#include "sobel_projet_opti.hpp"

using namespace std;
using namespace cv;
using std::cout;


void sobel_perso_opti (const Mat T , Mat G )
{
    uchar mh1, mh2, mh3, mh4, mv1, mv2, mv3, H1, V1, H2, V2, ad1, ad2, ad3, ad4;
    int  S[3] = {-1,-2,2};
    
    // ----------- Traitement de l'image sans ses bords --------------
    for (int i = 1; i<T.rows-1; i++)
    {
         for (int j = 1; j<T.cols-1; j+=2)
        {
            mh1 = T.at<uchar>(i - 1, j - 1) * S[0];
            mh2 = T.at<uchar>(i, j - 1) * S[1];
            mh3 = T.at<uchar>(i, j + 1) * S[2];
            mh4 = T.at<uchar>(i + 1, j - 1) * S[0];

            mv1 = T.at<uchar>(i - 1, j) * S[2];
            mv2 = T.at<uchar>(i + 1, j) * S[1];
            mv3 = T.at<uchar>(i + 1, j + 1) * S[0];

            H1 = mh1 + mh2;
            ad1 = mh3 + mh4;
            V1 = mh4 + mv1;
            ad2 = T.at<uchar>(i - 1, j + 1) + T.at<uchar>(i + 1, j + 1);
            ad3 = mv2 + mv3;
            ad4 = T.at<uchar>(i - 1, j - 1) + T.at<uchar>(i - 1, j + 1);

            mh1 = T.at<uchar>(i - 1, j) * S[0];
            H1 += ad1;
            mh2 = T.at<uchar>(i, j) * S[1];
            V1 += ad3;
            mh3 = T.at<uchar>(i, j + 2) * S[2];
            mh4 = T.at<uchar>(i + 1, j) * S[0];

            H1 += ad2;
            mv1 = T.at<uchar>(i - 1, j + 1) * S[2];
            V1 += ad4;
            mv2 = T.at<uchar>(i + 1, j + 1) * S[1];
            mv3 = T.at<uchar>(i + 1, j + 2) * S[0];

            H2 = mh1 + mh2;
            ad1 = mh3 + mh4;
            V2 = mh4 + mv1;
            ad2 = T.at<uchar>(i - 1, j + 2) + T.at<uchar>(i + 1, j + 2);
            ad3 = mv2 + mv3;
            ad4 = T.at<uchar>(i - 1, j) + T.at<uchar>(i - 1, j + 2);
            H2 += ad1;
            V2 += ad3;
            H2 += ad2;
            V2 += ad4;

            if (H1 < 0)
                H1 = abs(H1);
            if (V1 < 0)
                V1 = abs(V1);
            if (H1 < 225 && V1 < 225)
            	G.at<uchar>(i,j)= (H1+V1)/2;

            if (H2 < 0)
                H2 = abs(H2);
            if (V2 < 0)
                V2 = abs(V2);
            if (H2 < 225 && V2 < 225)
                G.at<uchar>(i, j+1) = (H2 + V2) / 2;
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
