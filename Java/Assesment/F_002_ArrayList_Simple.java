/*

For this problem, we have types of queries you can perform on a List:
1. Insert at index :
    Insert
    x y
2. Delete the element at index :
    Delete
    x
Given a list, , of integers, perform queries on the list. Once all queries are completed, print the
modified list as a single line of space-separated integers.
Input Format
    The first line contains an integer, (the initial number of elements in ).
    The second line contains space-separated integers describing .
    The third line contains an integer, (the number of queries).
    The subsequent lines describe the queries, and each query is described over two lines:
    If the first line of a query contains the String Insert, then the second line contains two space
    separated integers , and the value must be inserted into at index .
    If the first line of a query contains the String Delete, then the second line contains index , whose
    element must be deleted from .
Constraints
    Each element in is a 32-bit integer.
Output Format
    Print the updated list as a single line of space-separated integers.
Sample Input
    5
    12 0 1 78 12
    2
    Insert
    5 23
    Delete
    0
    2/2
Sample Output
    0 1 78 12 23

 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class F2_004_java_practice {
    public static void main(String[] args) {
        // Sample input
        int n = 5;
        List<Integer> list = new ArrayList<>(Arrays.asList(12, 0, 1, 78, 12));
        int q = 2;
        String[] commands = {
            "Insert", "5 23",
            "Delete", "0"
        };

        // Process queries
        for (int i = 0; i < q * 2; i += 2) {
            String action = commands[i];
            String data = commands[i + 1];

            if (action.equals("Insert")) {
                String[] parts = data.split(" ");
                int index = Integer.parseInt(parts[0]);
                int value = Integer.parseInt(parts[1]);
                list.add(index, value);
            } else if (action.equals("Delete")) {
                int index = Integer.parseInt(data);
                if (index >= 0 && index < list.size()) {
                    list.remove(index);
                }
            }
        }

        // Print the result
        for (int num : list) {
            System.out.print(num + " ");
        }
    }
}

/*
int q = 4;
        String[] commands = {
                "Insert", "5 23",
                "Insert", "1 100",
                "Delete", "0",
                "Delete", "2"
        };

100 0 78 12 23
*/
