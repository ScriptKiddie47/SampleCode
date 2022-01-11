/*Write a program to find the remainder when an integer A is divided by an integer B.

Input
The first line contains an integer T, the total number of test cases. Then T lines follow, each line contains two Integers A and B.

Output
For each test case, find the remainder when A is divided by B, and display it in a new line.

Constraints
1 = T = 1000
1 = A,B = 10000
Example
Input
3 
1 2
100 200
40 15

Output
1
100
10*/
#include <iostream>
#include <bits/stdc++.h>
using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	int t,a,b;
	cin >> t;
	int *A = new int[t];
	for(int i=0; i<t; i++) {
		cin >> a >> b;
		A[i] = a % b;

	}
	for(int i=0; i<t; i++) {
		cout << A[i] << endl;
	}
	return 0;
}
