/*Student Challenge 3:*/
/*Multiple Missing Element in Sorted Array*/

/*CASE1*/
/*{6,7,8,9,11,12,15,16,17,18,19} : 10,13 & 14 are missing*/
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
Once the difference is changed, we continue with the changed difference.

But what if the difference is More than 1, we just look through

*/
#include<iostream>
using namespace std;
int main() {
	int A[] = {6,7,8,9,11,12,15,16,17,18,19};
	int arraySize = sizeof(A)/sizeof(int);
	int commonDifference = A[0] - 0;
	for(int i=0; i<arraySize; i++) {
		if((A[i] - i) != commonDifference) {
			while(commonDifference < A[i]-i) {
				cout << "Missing number:" << i+commonDifference << endl;
				commonDifference++;
			}
		}
	}
}