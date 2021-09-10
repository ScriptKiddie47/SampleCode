public static void main(String[] args) {
		int[] arraySize = new int[10];
		int[] arrayUniqueCombinations = new int[10];

		// Set the initial few
		arraySize[0] = 1;
		arrayUniqueCombinations[0] = 1;

		int someArraySize = 5;

		for (int i = 1; i <= someArraySize; i++) {
			arraySize[i] = i;
			arrayUniqueCombinations[i] = arraySize[i] * arrayUniqueCombinations[i - 1];
		}

		for (int i = 0; i <= someArraySize; i++) {
			System.out.println(arraySize[i] + " : " + arrayUniqueCombinations[i]);
		}
	}