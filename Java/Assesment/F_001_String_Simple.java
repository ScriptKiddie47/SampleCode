/*

Given two strings of lowercase English letters, and , perform the following operations:
1. Sum the lengths of and .
2. Determine if is lexicographically larger than (i.e.: does come before in the dictionary?).
3. Capitalize the first letter in and and print them on a single line, separated by a space.
Input Format
    The first line contains a string . The second line contains another string . The strings are comprised of
    only lowercase English letters.
Output Format
There are three lines of output:
    For the first line, sum the lengths of and .
    For the second line, write Yes if is lexicographically greater than otherwise print No instead.
    For the third line, capitalize the first letter in both and and print them on a single line, separated by a space.
Sample Input 0
    hello
    java
Sample Output 0
    9
    No
    Hello Java

 */



public class F2_004_java_practice {
    public static void main(String[] args) {
        String A = "Karrat";
        String B = "Assesment";

        System.out.println(A.length() + B.length());
        if (A.compareTo(B) > 0)
            System.out.println("Yes");
        else
            System.out.println("No");
        A = A.substring(0, 1).toUpperCase() + A.substring(1);
        B = B.substring(0, 1).toUpperCase() + B.substring(1);
        System.out.println(A + " " + B);
    }
}

/*
Output:
15
Yes
Karrat Assesment
*/
