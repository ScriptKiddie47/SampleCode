/*Read problem statements in Bengali, Mandarin Chinese, Russian, and Vietnamese as well.
Chef is going on a road trip and needs to apply for inter-district and inter-state travel e-passes. It takes A minutes to fill each inter-district e-pass application and B minutes for each inter-state e-pass application.

His journey is given to you as a binary string S of length N where 0 denotes crossing from one district to another district (which needs an inter-district e-pass), and a 1 denotes crossing from one state to another (which needs an inter-state e-pass).

Find the total time Chef has to spend on filling the various forms.

Input Format
The first line of the input contains a single integer T denoting the number of test cases. The description of T test cases follows.
Each test case contains two lines of input.
First line contains three space separated integers N,A and B.
Second line contains the string S.
Output Format
For each testcase, output in a single line the total time Chef has to spend on filling the various forms for his journey.

Constraints
1≤T≤102
1≤N,A,B≤102
Si∈{′0′,′1′}
Subtasks
Subtask #1 (100 points): original constraints

Sample Input 1 
3
2 1 2
00
2 1 1
01
4 2 1
1101
Sample Output 1 
2
2
5
Explanation
Test case 1: Chef needs total 2 inter-district e-passes, and he will be filling them in total 1⋅2=2 minutes.

Test case 3: Chef needs total 1 inter-district e-pass and 3 inter-state e-passes, and he will be filling them in total 2⋅1+1⋅3=5 minutes.


/* package codechef; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner in = new Scanner(System.in);

		ArrayList<int[]> arr2 = new ArrayList<int[]>();
		ArrayList<String> binaryStringList = new ArrayList<String>();

		int numberOfTestCases = in.nextInt();
		in.nextLine();
		int inputValue = 0;
		if (numberOfTestCases >= 1 && numberOfTestCases <= 100) {
			for (int i = 0; i < numberOfTestCases; i++) {
				int[] arr1 = new int[3];
				for (int j = 0; j < 3; j++) {
					inputValue = in.nextInt();
					if (inputValue >= 1 && inputValue <= 100) {
						arr1[j] = inputValue;
					}
				}
				in.nextLine();
				arr2.add(arr1);
				String binaryString = in.nextLine();
				binaryStringList.add(binaryString);
			}
		}
		
		int time = 0;
		int counter = 0;
		for (int[] a : arr2) {
			char[] c = binaryStringList.get(counter).toCharArray();
			counter++;
			for (int i = 0; i < c.length; i++) {
				int number = Character.getNumericValue(c[i]);
				if(number == 0) {
					time+=a[1];
				}else if(number == 1) {
					time+=a[2];
				}
			}
			System.out.println(time);
			time = 0;
		}

		in.close();
	}
}
