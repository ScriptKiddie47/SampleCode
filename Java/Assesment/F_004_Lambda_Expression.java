/*

Java Lambda
Expressions
This Java 8 challenge tests your knowledge of Lambda expressions!
Write the following methods that return a lambda expression performing a specified action:
1. isOdd(): The lambda expression must return if a number is odd or if it
is even.
2. isPrime(): The lambda expression must return if a number is prime or
if it is composite.
3. isPalindrome(): The lambda expression must return if a number is a
palindrome or if it is not.

Output Format
    print lines of output.
Sample Input
The first line contains an integer, (the number of test cases).
The subsequent lines each describe a test case in the form of space-separated integers:
The first integer specifies the condition to check for ( for Odd/Even, for Prime, or for Palindrome).
The second integer denotes the number to be checked.
    5
    1 4
    2 5
    3 898
    1 3
    2 12
Sample Output
    EVEN
    PRIME
    PALINDROME
    ODD
    COMPOSITE

 */


import java.util.function.Predicate;

public class F2_004_java_practice {
    public static void main(String[] args) {
        // Define test cases (first value = condition code, second = number)
        int[][] testCases = {
            {1, 4},
            {2, 5},
            {3, 898},
            {1, 3},
            {2, 12}
        };

        // Lambda for checking EVEN
        Predicate<Integer> isEven = n -> n % 2 == 0;

        // Lambda for checking PRIME
        Predicate<Integer> isPrime = n -> {
            if (n <= 1) return false;
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) return false;
            }
            return true;
        };

        // Lambda for checking PALINDROME
        Predicate<Integer> isPalindrome = n -> {
            String str = String.valueOf(n);
            return str.equals(new StringBuilder(str).reverse().toString());
        };

        // Process each test case
        for (int[] testCase : testCases) {
            int condition = testCase[0];
            int number = testCase[1];

            switch (condition) {
                case 1:
                    System.out.println(isEven.test(number) ? "EVEN" : "ODD");
                    break;
                case 2:
                    System.out.println(isPrime.test(number) ? "PRIME" : "COMPOSITE");
                    break;
                case 3:
                    System.out.println(isPalindrome.test(number) ? "PALINDROME" : "NOT PALINDROME");
                    break;
                default:
                    System.out.println("INVALID CONDITION");
            }
        }
    }
}
