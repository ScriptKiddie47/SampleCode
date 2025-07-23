/*
A string containing only parentheses is balanced if the following is true: 1. if it is an empty string 2. if A
and B are correct, AB is correct, 3. if A is correct, (A) and {A} and [A] are also correct.
Examples of some correctly balanced strings are: "{}()", "[{()}]", "({()})"
Examples of some unbalanced strings are: "{}(", "({)}", "[[", "}{" etc.
Given a string, determine if it is balanced or not.
Input Format
	There will be multiple lines in the input file, each having a single non-empty string. You should read input
till end-of-file.
The part of the code that handles input operation is already provided in the editor.
Output Format
	For each case, print 'true' if the string is balanced, 'false' otherwise.
Sample Input
	{}()
	({()})
	{}(
	[]
Sample Output
	true
	true
	false
	true
/*



import java.util.Stack;

public class F2_004_java_practice {
    public static void main(String[] args) {
        String[] inputs = {
                "{}()",
                "({()})",
                "{}(",
                "[]"
        };

        for (String s : inputs) {
            System.out.println(isBalanced(s));
        }
    }

    static boolean isBalanced(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty())
                    return false;
                char open = stack.pop();
                if ((c == ')' && open != '(') ||
                        (c == '}' && open != '{') ||
                        (c == ']' && open != '[')) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
