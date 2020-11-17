package com.scripter;

import java.util.Arrays;
import java.util.List;

public class Ds {

	public static void main(String[] args) {
		List<Integer> values = Arrays.asList(4,5,6,7);
		values.forEach(i->System.out.println(i)); //Lambda Expression
	}
}
