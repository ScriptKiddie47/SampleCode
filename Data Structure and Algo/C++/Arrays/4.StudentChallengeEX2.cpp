/*Student Challenge 2:*/
/*Single Missing Element in Sorted Array*/

/*CASE1*/
/*Sequnce is n natutals numbers*/
/*{6,7,8,9,10,11,13,14,15,16,17}*/
/*
Method 1: Use Indices
6 is 0 index
7 is 1 index
So,
6-0 = 6
7-1 = 6
8-2 = 6
So once we arrive on
13-6 = 7 which is !=6
So here just add the index
index+Common Difference = Required Value

*/
#include<iostream>
using namespace std;
int main() {
	int A[] = {6,7,8,9,10,11,13,14,15,16,17};
	int arraySize = sizeof(A)/sizeof(int);
	int commonDifference = A[0] - 0;
	for(int i=0; i<arraySize; i++) {
		if((A[i] - i) != commonDifference) {
			cout << "Missing number:" << i+commonDifference;
			break;
		}
	}
}
