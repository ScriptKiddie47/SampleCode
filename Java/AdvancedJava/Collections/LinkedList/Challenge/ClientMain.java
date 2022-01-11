// Create a program that implements a simple mobile phone with the following capabilities.
// Able to store, modify, remove and query contact names.
// You will want to create a separate class for Contacts (name and phone number).
// Create a master class (MobilePhone) that holds the ArrayList of Contacts
// The MobilePhone class has the functionality listed above.
// Add a menu of options that are available.
// Options:  Quit, print list of contacts, add new contact, update existing contact, remove contact
// and search/find contact.
// When adding or updating be sure to check if the contact already exists (use name)
// Be sure not to expose the inner workings of the Arraylist to MobilePhone
// e.g. no ints, no .get(i) etc
// MobilePhone should do everything with Contact objects only.

package com.scripter;

import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int userInputNumber = 0;
        do {
            System.out.println("--------------------------------------");
            System.out.println("Select Among the following Operation");
            System.out.println("1. add new contact");
            System.out.println("2. update existing contact");
            System.out.println("3. print list of contacts");
            System.out.println("4. remove contact");
            System.out.println("5. search/find contact");
            System.out.println("6. Quit");
            userInputNumber = in.nextInt();
            in.nextLine();
            switch (userInputNumber) {
                case 1:
                    System.out.println("Name");
                    String name = in.nextLine();
                    System.out.println("Number:");
                    String number = in.nextLine();
                    MobilePhone.addNewContact(name, number);
                    break;
                case 2:
                    System.out.println("Name");
                    name = in.nextLine();
                    System.out.println("Number:");
                    number = in.nextLine();
                    MobilePhone.updateExistingContact(name, number);
                    break;
                case 3:
                    MobilePhone.printListOfContacts();
                    break;
                case 4:
                    System.out.println("Name");
                    name = in.nextLine();
                    MobilePhone.removeContact(name);
                    break;
                case 5:
                    System.out.println("Name");
                    name = in.nextLine();
                    MobilePhone.searchfFindContact(name);
                    break;
                case 6:
                    System.out.println("Tool Exit Selected");
                default:
                    System.out.println("Wrong option Selected");
            }
        } while (userInputNumber != 6);
    }
}

