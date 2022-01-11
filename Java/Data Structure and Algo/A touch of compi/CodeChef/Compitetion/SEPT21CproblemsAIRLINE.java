https://www.codechef.com/SEPT21C/problems/AIRLINE
/*
Read problem statements in Bengali, Mandarin Chinese, Russian, and Vietnamese as well.
Chef has 3 bags that she wants to take on a flight. They weigh A, B, and C kgs respectively. She wants to check-in exactly two of these bags and carry the remaining one bag with her.

The airline restrictions says that the total sum of the weights of the bags that are checked-in cannot exceed D kgs and the weight of the bag which is carried cannot exceed E kgs. Find if Chef can take all the three bags on the flight.

Input Format
The first line of the input contains a single integer T denoting the number of test cases. The description of T test cases follows.
Each testcase contains a single line of input, five space separated integers A,B,C,D,E.
Output Format
For each testcase, output in a single line answer "YES" if Chef can take all the three bags with her or "NO" if she cannot.

You may print each character of the string in uppercase or lowercase (for example, the strings "yEs", "yes", "Yes" and "YES" will all be treated as identical).

Constraints
1≤T≤36000
1≤A,B,C≤10
15≤D≤20
5≤E≤10
Subtasks
Subtask #1 (100 points): original constraints

Sample Input 1 
3
1 1 1 15 5
8 7 6 15 5
8 5 7 15 6
Sample Output 1 
YES
NO
YES
Explanation
Test case 1: Chef can check-in the first and second bag (since 1+1=2≤15) and carry the third bag with her (since 1≤5).

Test case 2: None of the three bags can be carried in hand without violating the airport restrictions.

Test case 3: Chef can check-in the first and the third bag (since 8+7≤15) and carry the second bag with her (since 5≤6).

Author:	daanish_adm
Date Added:	30-08-2021
Time Limit:	0.5 secs
Source Limit:	50000 Bytes
Languages:	CPP14, C, JAVA, PYTH 3.6, CPP17, PYTH, PYP3, CS2, ADA, PYPY, TEXT, PAS fpc, NODEJS, RUBY, PHP, GO, HASK, TCL, PERL, SCALA, LUA, kotlin, BASH, JS, LISP sbcl, rust, PAS gpc, BF, CLOJ, R, D, CAML, FORT, ASM, swift, FS, WSPC, LISP clisp, SQL, SCM guile, PERL6, ERL, CLPS, ICK, NICE, PRLG, ICON, COB, SCM chicken, PIKE, SCM qobi, ST, SQLQ, NEM

*/
package com.Scripter;

import java.util.ArrayList;
import java.util.Scanner;

public class ClientMain {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		ArrayList<int[]> arr2 = new ArrayList<int[]>();
		int numberOfTestCases = in.nextInt();
		in.nextLine();
		int inputValue = 0;
		for (int i = 0; i < numberOfTestCases; i++) {
			int[] arr1 = new int[5];
			for (int j = 0; j < 5; j++) {
				inputValue = in.nextInt();
				if (j == 0 || j == 1 || j == 2) {
					if (inputValue >= 1 && inputValue <= 10) {
						arr1[j] = inputValue;
					}
				} else if (j == 3 && (inputValue >= 15 && inputValue <= 20)) {
					arr1[j] = inputValue;
				} else if (j == 4 && (inputValue >= 5 && inputValue <= 10)) {
					arr1[j] = inputValue;
				}
			}
			arr2.add(arr1);
		}


		for (int[] a : arr2) {
			if (a[0] + a[1] <= a[3] && (a[2] <= a[4])) {
				System.out.println("YES");
			} else if (a[1] + a[2] <= a[3] && (a[0] <= a[4])) {
				System.out.println("YES");
			} else if (a[0] + a[2] <= a[3] && a[1] <= a[4]) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
		in.close();
	}
}
