/*Student Challenge 1:*/
/*Single Missing Element in Sorted Array*/

/*CASE1*/
/*Sequnce is n natutals numbers*/
/*[1,2,3,4,5,6,8,9,10,11,12] : 7 is missing obviously*/

/*
Method 1: Sum of first N natural numbers.
Only Works when we know the first number is 1 and the last number is known to US.
Well to be honest, we can fetch the last number
*/
/*
Formula of 1st n natural numbers: n(n+1)/2
*/
#include<iostream>
using namespace std;
int main() {
	int A[] = {1,2,3,4,5,6,8,9,10,11,12};
	int sum = 0;
	int arraySize = sizeof(A)/sizeof(int);
	for(int i=0; i<arraySize; i++) {
		sum = sum + A[i];
	}
	int sum2 = A[arraySize-1]*(A[arraySize-1]+1)/2;
	cout << "Missing number:" << sum2 - sum;
}