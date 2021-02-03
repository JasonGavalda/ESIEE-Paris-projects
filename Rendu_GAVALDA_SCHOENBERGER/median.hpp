#include<math.h>
#include <iostream>

#include <opencv2/highgui.hpp>
#include <opencv2/opencv.hpp>
#include <opencv2/imgproc/imgproc.hpp>
#include <opencv2/highgui/highgui.hpp>

using namespace std;
using namespace cv;
using std::cout;

void buildHisto(Mat image, uchar histogram[256], int i, int j);

uchar median_perso(uchar histogram[256]);

void computeMedianMat(Mat image, Mat medianMat);

void addColumn(Mat image, uchar histogram[256], int i, int j);

void removeColumn(Mat image, uchar histogram[256], int i, int j);

void computeMedianMatOpti(Mat image, Mat medianMat) ;
