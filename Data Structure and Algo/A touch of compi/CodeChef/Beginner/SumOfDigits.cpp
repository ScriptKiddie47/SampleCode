/*Sum of Digits*/
/*You're given an integer N. Write a program to calculate the sum of all the digits of N.

Input
The first line contains an integer T, the total number of testcases. Then follow T lines, each line contains an integer N.

Output
For each test case, calculate the sum of digits of N, and display it in a new line.

Constraints
1 = T = 1000
1 = N = 1000000
Example
Input
3
12345
31203
2123
Output
15
9
8*/
#include <iostream>
#include <bits/stdc++.h>
using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	int t,a,sum;
	cin >> t;
	int *A = new int[t];
	for(int i=0; i<t; i++) {
		cin >> a;
		sum =0;
		int r = 0;
		while(a != 0) {
			r = a % 10;
			a = a / 10;
			sum = sum + r;
		}
		A[i] = sum;

	}
	for(int i=0; i<t; i++) {
		cout << A[i] << endl;
	}
	return 0;
}
