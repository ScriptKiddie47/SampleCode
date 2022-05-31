 // https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/function/BiFunction.html

/*

Interface BiFunction<T,​U,​R>
Type Parameters:
T - the type of the first argument to the function
U - the type of the second argument to the function
R - the type of the result of the function

Represents a function that accepts two arguments and produces a result. This is the two-arity specialization of Function.

*/


	public static void main(String[] args) {
		_function_2();
	}

	public static void _function_2() {
		System.out.println(increment1AndMultiplly(4, 100)); // 500
		System.out.println(increment1AndMultiplly(4, 100)); // 500
	}

	BiFunction<Integer, Integer, Integer> increment1AndMultipllyBIFunction = (t, u) -> (t + 1) * u;

	public static int increment1AndMultiplly(int number, int numToMultipyBy) {
		return (number + 1) * numToMultipyBy;
	}
