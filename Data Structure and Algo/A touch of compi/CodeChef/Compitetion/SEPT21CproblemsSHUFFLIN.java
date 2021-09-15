package com.Scripter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ClientMain {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		ArrayList<int[]> arr2 = new ArrayList<int[]>();
		int[] arraySize = new int[100000];

		int numberOfTestCases = in.nextInt();
		in.nextLine();
		int inputValue = 0;

		if (numberOfTestCases >= 1 && numberOfTestCases <= 10000) { // 1≤T≤10 4
			for (int i = 0; i < numberOfTestCases; i++) {
				int sizeOfArray = in.nextInt();
				in.nextLine();

				if (sizeOfArray >= 1 && sizeOfArray <= 100000) { // 1≤N≤10 5
					int[] arr1 = new int[sizeOfArray];
					arraySize[i] = sizeOfArray;

					for (int j = 0; j < sizeOfArray; j++) {
						inputValue = in.nextInt();
						if (inputValue >= 1 && inputValue <= 1000000000) { // 1≤Ai≤10 9
							arr1[j] = inputValue;
						}
					}
					arr2.add(arr1);
				}
			}
		}

//		for (int[] a : arr2) {
//			System.out.println(Arrays.toString(a));
//		}

		int possibleEven = 0;
		int possibleOdd = 0;

		for (int[] a : arr2) {
			int weGetEven = 0;
			int weGetOdd = 0;
			int actualEven = 0;
			int actualOdd = 0;
			
			possibleOdd = a.length / 2;
			possibleEven = a.length - possibleOdd;

			for (int i = 0; i < a.length; i++) {
				
				if (a[i] % 2 == 1) {
					actualOdd++;
				} else {
					actualEven++;
				}
			}

			// 3 
			// Expected 2 even got 1. We can only accept 1
			// Expected 1 odd got 2. We can only accept 1
			
			if(possibleEven > actualEven) {
				weGetEven = actualEven;
			}else {
				weGetEven = possibleEven;
			}
			
			if(possibleOdd > actualOdd) {
				weGetOdd = actualOdd ;
			}else {
				weGetOdd = possibleOdd;
			}
			
			System.out.println(weGetEven+weGetOdd);
		}
	}
}