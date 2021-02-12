package com.something;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.IntStream;

public class OneShot {
	public static void main(String[] args) {
		int anArray[] = { 2, 3, 9, 5, 7, 6, 9 };
		ArrayList<int[]> myArrayList = new ArrayList<int[]>();
		int j = 0, k = 0;
		for (int i = 0; i < anArray.length - 2; i++) {
			for (j = i + 1; j < anArray.length - 1; j++) {
				for (k = j + 1; k < anArray.length; k++) {
					int supportArray[] = new int[3];
					supportArray[0] = anArray[i];
					supportArray[1] = anArray[j];
					supportArray[2] = anArray[k];
					myArrayList.add(supportArray);
				}
			}
		}
		//Print An arrayList
		for(int[] t:myArrayList) {
			System.out.println(Arrays.toString(t));
		}
	}

}
