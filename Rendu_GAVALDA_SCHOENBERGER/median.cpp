#include<math.h>
#include <iostream>

#include <opencv2/highgui.hpp>
#include <opencv2/opencv.hpp>
#include <opencv2/imgproc/imgproc.hpp>
#include <opencv2/highgui/highgui.hpp>

#include "median.hpp"


using namespace std;
using namespace cv;
using std::cout;

// Helping functions median
//------------------------------------------------------



const int kernelSize = 3;
const int kernelTotalSize = 1 + 2 * kernelSize;


void buildHisto(Mat image, unsigned histogram[256], int i, int j)
{
	unsigned color;
	for( int k = i - kernelSize; k <= i + kernelSize; k++){
        for( int p = j - kernelSize; p <= j + kernelSize; p++){
			if(k >= 0 && p >= 0 && k < image.rows && p < image.cols){
				color = image.at<uchar>(k,p);
				histogram[color] ++;
			}
			else{
				color = image.at<uchar>(i,j);
				histogram[color] ++;
			}
			
		}
    }
}

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

unsigned median_perso(unsigned histogram[256])
{
    int medianSum = (kernelTotalSize * kernelTotalSize ) / 2;
    int sum = 0;
    int index = -1;
    while(sum <= medianSum){
		index ++;
        sum += histogram[index];
    }
    return(index);	
}

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

void computeMedianMat(Mat image, Mat medianMat)
{
    unsigned * histogram = (unsigned *) calloc(256, sizeof(unsigned) );
	
    for(int i = 0; i < image.rows; i++ ){
        for(int j = 0; j < image.cols; j++){

           	buildHisto(image,histogram,i,j);
			medianMat.at<uchar>(i,j) = median_perso(histogram);
			free(histogram);	
			histogram = (unsigned *) calloc(256, sizeof(unsigned) );
        }
    }
    free(histogram);
}

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

void addColumn(Mat image, unsigned histogram[256], int i, int j) // DO NOT TEST BORDERS
{
	unsigned color;
	for(int k = - kernelSize; k <= kernelSize; k++)
	{
		color = image.at<uchar>(i + k ,j + kernelSize);
		histogram[color]++;
	}
}

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

void removeColumn(Mat image, unsigned histogram[256], int i, int j) // DO NOT TEST BORDERS
{
	unsigned color;
	for(int k = - kernelSize; k <= kernelSize; k++)
	{
		color = image.at<uchar>(i + k ,j - kernelSize - 1);
		histogram[color]--;
	}
}

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------


void computeMedianMatOpti(Mat image, Mat medianMat) // HAS TO TEST BORDERS
{
    unsigned * histogram = (unsigned *) calloc(256, sizeof(unsigned) );
    for(int i = kernelSize; i < image.rows - kernelSize; i++ ){
		
	buildHisto(image,histogram,i,kernelSize);
	medianMat.at<uchar>(i,kernelSize) = median_perso(histogram);
		
        for(int j = kernelSize + 1; j < image.cols - kernelSize; j++){
			
			addColumn(image, histogram, i, j);
			removeColumn(image, histogram, i, j);
           	medianMat.at<uchar>(i,j) = median_perso(histogram);				
        }
		free(histogram);
		histogram = (unsigned *) calloc(256, sizeof(unsigned));
    }
    free(histogram);
}
