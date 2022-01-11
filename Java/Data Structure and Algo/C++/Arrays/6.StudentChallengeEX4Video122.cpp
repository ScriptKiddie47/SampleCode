/*
Finding Missing Elements in an Array Method 2

int A[] = {3,7,4,9,12,6,1,11,2,10};

Data Known to user
Lowest Element = 1
Highest Element = 12

---------------------------
STEP 1: Take an array equal to the size of the Max element
int newArray[12];

STEP 2: Initialize Array with Zero
int newArray[12] = {0,0,0,0....0};

STEP 3 : Scan through the OG array
Say 1st element is 3 from A[] array.
Go to index 3 of newArray & Increment it
Same process repeats
*/
#include<iostream>
using namespace std;
int main() {
	int A[] = {1,2,3,4,5,6,8,9,10,11,12};
	int myArray[13] = {0};
	int sum = 0;
	int arraySize = sizeof(A)/sizeof(int);
	int temp = 0;
	for(int i=0; i<arraySize; i++) {
		temp = A[i];
		myArray[temp]++;
	}
	cout << "Missing Element :";
	for(int i=1; i<13; i++) {
		if(myArray[i]==0){
			cout << i << endl;
		}
	}
}