package com.scripter;

interface A{
	void show();
}
class Xyz implements A{

	@Override
	public void show() {
		System.out.println("Hello");	
	}
}
public class Ds {

	public static void main(String[] args) {
			A obj;
			obj = new Xyz();
			obj.show();
	}
}
