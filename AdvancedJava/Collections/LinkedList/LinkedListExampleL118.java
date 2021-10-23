package com.scripter;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

//Note : There is a fundamental issue with the Traverse Code here in the function  : visit

public class ClientMain {
    public static void main(String[] args) {
        LinkedList<String> placesToVisit = new LinkedList<String>();
        addInOrder(placesToVisit, "Melbourne");
        addInOrder(placesToVisit, "Sydney");
        addInOrder(placesToVisit, "Brisbane");
        addInOrder(placesToVisit, "Perth");
        addInOrder(placesToVisit, "Adelaide");
        addInOrder(placesToVisit, "Canberra");
        addInOrder(placesToVisit, "Melbourne");
        addInOrder(placesToVisit, "Alice Springs");

        printList(placesToVisit);
        visit(placesToVisit);
    }

    private static void printList(LinkedList<String> placesToVisit) {
        Iterator<String> placesToVisitIterator = placesToVisit.iterator();
        while (placesToVisitIterator.hasNext()) {
            System.out.println("Now Visiting : " + placesToVisitIterator.next());
        }
        System.out.println("=====================================");
    }

    /*
     * the value 0 if the argument string is equal to this string; a value less
     * than 0 if this string is lexicographically less than the string argument;
     * and a value greater than 0 if this string is lexicographically greater
     * than the string argument.
     */
    private static boolean addInOrder(LinkedList<String> cities, String newCity) {
        ListIterator<String> citiesIterator = cities.listIterator();
        while (citiesIterator.hasNext()) {
            int comparison = citiesIterator.next().compareTo(newCity);
            if (comparison > 0) {
                citiesIterator.previous();
                citiesIterator.add(newCity);
                return true;
            } else if (comparison == 0) {
                System.out.println(newCity + " is already included");
                return false;
            }
        }
        citiesIterator.add(newCity);
        return true;
    }

    private static void visit(LinkedList<String> cities) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        ListIterator<String> citiesIterator = cities.listIterator();
        if (cities.isEmpty()) {
            System.out.println("No Cities in the Itinerary");
            return;
        } else {
            System.out.println("Now Visiting:" + citiesIterator.next());
            printMenu();
        }

        while (!quit) {
            int action = scanner.nextInt();
            scanner.nextLine();
            switch (action) {
                case 0:
                    System.out.println("Holiday Over");
                    quit = true;
                    break;
                case 1:
                    if (citiesIterator.hasNext()) {
                        System.out.println("Now Visiting:" + citiesIterator.next());
                    } else {
                        System.out.println("Reached End of the List");
                    }
                    break;
                case 2:
                    if (citiesIterator.hasPrevious()) {
                        System.out.println("Now Visiting:" + citiesIterator.previous());
                    } else {
                        System.out.println("Reached Start of the List");
                    }
                    break;
                case 3:
                    printMenu();
                    break;
            }

        }
    }

    private static void printMenu() {
        System.out.println("Available Actions:\npress");
        System.out.println("" +
                "0 - to quit\n"+
                "1 - go to next city\n"+
                "2 - go to previous city\n"+
                "3 - print me options");
    }
}
