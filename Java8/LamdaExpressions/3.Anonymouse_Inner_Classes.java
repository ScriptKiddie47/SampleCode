package com.scripter;

interface A{
	void show();
}
public class Ds {

	public static void main(String[] args) {
		A obj;
		obj = new A() {

			@Override
			public void show() {
				System.out.println("Hello");
			}
		};
		obj.show();
	}
}
