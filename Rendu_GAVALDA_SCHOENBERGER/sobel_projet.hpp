#include<math.h>
#include <iostream>

#include <opencv2/highgui.hpp>
#include <opencv2/opencv.hpp>
#include <opencv2/imgproc/imgproc.hpp>
#include <opencv2/highgui/highgui.hpp>

using namespace std;
using namespace cv;
using std::cout;

void SobelH(Mat T, int * S, int * H, int i, int j);

void SobelV(Mat T, int *S, int * V, int i, int j);

void sobelperso (Mat T , Mat G );
