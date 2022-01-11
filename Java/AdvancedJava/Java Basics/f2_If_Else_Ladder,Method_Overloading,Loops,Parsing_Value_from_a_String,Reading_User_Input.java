/*
The below code expands on
1. ++,-- Operators
2. If-Else Ladder,
3. Method Overloading,
4. Loops,Parsing Value from a String,
5. Reading User Input Using Scanner
 */


package com.revisted.main;

import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {

        //Operators
        //Abbreviation Operator

        int result = 5;

        System.out.println("result : " + result); //5
        result++;
        System.out.println("result : " + result); //6
        result--;
        System.out.println("result : " + result); //5
        System.out.println("result++ : " + result++); //5
        System.out.println("result++ : " + result); //6

        System.out.println("result++ : " + result--); //6
        System.out.println("result++ : " + result); //5

        System.out.println("result++ : " + ++result); //6
        System.out.println("result++ : " + result); //6

        System.out.println("------------------------------------------------");

        //No Error here
        boolean isCar = false;
        if (isCar = true) {
            System.out.println(isCar);
        }

        System.out.println("------------------------------------------------");

        //Simple If Else ladder
        int score = 5000;

        if (score == 5000) {
            System.out.println("5000");
        } else if (score < 1000) {
            System.out.println("Less than 5000");
        } else {
            System.out.println("OUT");
        }

        //Calling a method
        System.out.println("calculateScore(5,5):" + calculateScore(5, 5)); //10
        System.out.println("calculateScore(5,5,5):" + calculateScore(5, 5, 5)); //15


        System.out.println("------------------------------------------------");

        Day[] daysOfWeek = Day.values();
        for (Day d : daysOfWeek) {
            System.out.println("\nFor Day:" + d);
            //Switch Case
            switch (d) {
                default:
                    System.out.println("Some Day");
                case MONDAY:
                    System.out.println("Monday Boy");
                case TUESDAY:
                    System.out.println("Tuesday Boy");
            }
        }

        System.out.println("------------------------------------------------");

        //loop
        int i = 0;
        while (i < 10) {
            System.out.print(i);
            i++;
        }

        //Parsing Values from a String "2018"
        String numberAsString = "2018";
        System.out.println("numberAsString : " + numberAsString); //2018

        int number = Integer.parseInt(numberAsString);
        System.out.println("number : " + number); //2018

        //Parsing Values from a String "2018D"
        String numberAsString2 = "2018D";
        System.out.println("numberAsString2 : " + numberAsString2); //2018D

//        int number2 = Integer.parseInt(numberAsString2); //java.lang.NumberFormatException.forInputString
//        System.out.println("number2 : "+ number2);


        System.out.println("------------------------------------------------");

        //Reading User Input
        // new -> Create Instance of Class
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Your Year of Birth : ");
        int myYrOfBirth = scanner.nextInt();

        scanner.nextLine();

        System.out.println("Enter Your Name : ");
        String myName = scanner.nextLine();
        System.out.println("Name : " + myName + " , Year of Birth : " + myYrOfBirth);

        //Close Scanner
        scanner.close();
    }

    //Introduction to Method
    public static int calculateScore(int newScore, int oldScore) {
        return newScore + oldScore;
    }


    //Method Overloading
    public static int calculateScore(int newScore, int oldScore, int medScore) {
        return newScore + oldScore + medScore;
    }

    //In Java, Method Overloading is not possible by changing the return type of the method only.
    //There are two ways to overload the method in java
    //    1. By changing number of arguments
    //    2. By changing the data type

    //Enum Switch Case

    public enum Day {
        MONDAY, TUESDAY, WEDNESDAY
    }


}
