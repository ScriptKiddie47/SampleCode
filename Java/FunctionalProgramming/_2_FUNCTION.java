/*

Interface Function<T,​R>
Type Parameters:
T - the type of the input to the function
R - the type of the result of the function

Represents a function that accepts one argument and produces a result.

*/




	public static void _function_2() {
		int increment = increment1(1);
		int increment2 = incrementByOneFunction.apply(2);
		System.out.println("increment1 Simple Function : " + increment + " incrementByOneFunction :" + increment2);
		
		int multiply = multipleBy10Function.apply(increment2);
		System.out.println("MULTIPLE BY 10 : " + multiply);
		
		Function<Integer, Integer> addBy1AndThenMultipleBy10 = incrementByOneFunction.andThen(multipleBy10Function);
		
		System.out.println(addBy1AndThenMultipleBy10.apply(2));
	}

	static Function<Integer, Integer> incrementByOneFunction = num -> num++; // This is Exactly similar to increment1 (
																				// int number ) Function
	static Function<Integer, Integer> multipleBy10Function = num -> num * 10;
	
	public static int increment1 (int number) {
		return number+1;
	}