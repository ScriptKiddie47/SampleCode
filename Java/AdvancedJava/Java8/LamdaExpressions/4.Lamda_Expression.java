package com.scripter;

interface A{
	void show();
}
interface B{
	void show2(int i);
}
public class Ds {

	public static void main(String[] args) {
		A obj;
		obj = () ->{
			System.out.println("Hello");
		};
		A obj2 = () -> System.out.println("Hello"); 
		B obj3 = (int i) -> System.out.println("Hello:"+i);
		B obj4 = (i) -> System.out.println("Hello:"+i);
		obj.show();
		obj2.show();
		obj3.show2(5);
		obj4.show2(9);

	}
}
