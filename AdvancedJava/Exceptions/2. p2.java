package com.ScriptKiddie;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Example {
    public static void main(String[] args) {
        int result = divide();
        System.out.println(result);
    }

    private static int divide() {
        int x;
        int y;
        try {
            x = getInt();
            y = getInt();
        } catch (NoSuchElementException e) {
           throw new ArithmeticException("No Suitable Input");
        }
        System.out.println("x :" + x + " y :" + y);
        try{
            return x / y;
        }catch (ArithmeticException e){
            throw new ArithmeticException("Attempt to Divide by Zero");
        }
    }

    private static int getInt() {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter an integer");
        while (true) {
            try {
                return s.nextInt();
            } catch (InputMismatchException e) {
                // go around again, Read past the end of the line
                s.nextLine();
                System.out.println("Please Enter a number using only the digits 0 - 9");
            }
        }
    }
}
