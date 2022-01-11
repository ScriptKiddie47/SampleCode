package com.scripter;

import java.util.ArrayList;

public class MobilePhone {
    public static ArrayList<Contacts> contactsArrayList = new ArrayList<>();

    public static void addNewContact(String name, String number) {
        boolean nameNonExistent = false;
        //When adding or updating be sure to check if the contact already exists (use name)
        for (Contacts c : contactsArrayList) {
            if (c.getName().equals(name)) {
                nameNonExistent = true;
                break;
            }
        }
        if (!nameNonExistent) {
            contactsArrayList.add(new Contacts(name, number));
            System.out.println("Added");
        }
    }

    public static void printListOfContacts() {
        if (contactsArrayList != null && contactsArrayList.size() > 0) {
            for (Contacts c : contactsArrayList) {
                System.out.println(c.toString());
            }
        } else {
            System.out.println("Empty Contact List");
        }
    }

    public static void updateExistingContact(String name, String number) {
        boolean nameNonExistent = false;
        int index = 0;
        //When adding or updating be sure to check if the contact already exists (use name)
        for (Contacts c : contactsArrayList) {
            if (c.getName().equals(name)) {
                nameNonExistent = true;
                break;
            }
            index++;
        }
        if (nameNonExistent) {
            contactsArrayList.set(index, new Contacts(name, number));
            System.out.println("Updated");
        } else {
            System.out.println("No such name exists in the system to update");
        }
    }

    public static void removeContact(String name) {
        boolean nameNonExistent = false;
        int index = 0;
        //When adding or updating be sure to check if the contact already exists (use name)
        for (Contacts c : contactsArrayList) {
            if (c.getName().equals(name)) {
                nameNonExistent = true;
                break;
            }
            index++;
        }
        if (nameNonExistent) {
            contactsArrayList.remove(contactsArrayList.get(index));
            System.out.println("Deleted");
        } else {
            System.out.println("No such name exists in the system to Delete");
        }
    }

    public static void searchfFindContact(String name) {
        boolean nameNonExistent = false;
        for (Contacts c : contactsArrayList) {
            if (c.getName().equals(name)) {
                System.out.println(c.toString());
                nameNonExistent = true;
                break;
            }
        }
        if (!nameNonExistent) {
            System.out.println("No Such Contact");
        }
    }
}
