package com.scriptKiddie;

public class mainClass {
    public static void main(String args[]) {

        /*
        Variables in BankAccount Class
        private int accountNumber;
        private double balance;
        private String customerName;
        private String email;
        private int phoneNumber;
        */

        BankAccount mark = new BankAccount();
        BankAccount hamil = new BankAccount(56479,365900.25,"Hamil","hamil@xyz.com",859956436);


        mark.setBalance(25000.25);

        System.out.println("Balance:" + mark.getBalance());
        mark.depositFund(36.25);
        mark.withdrawFunds(25.98);
        mark.withdrawFunds(3666565);

        //For Hamil
        //System.out.println("Object ID:"+hamil.getClass().getName());
        hamil.displayAll();
        System.out.println("\n\n");
        mark.displayAll();

        //VipCustomer
        VipCustomer vip1 = new VipCustomer();
        VipCustomer vip2 = new VipCustomer("Dark",25666.25);
        System.out.println("\n\n");
        vip1.displayAll();
        System.out.println("\n\n");
        vip2.displayAll();


        System.out.println("\n\n");
        Animal animalObj = new Animal("animal",1,1,5,5);
        animalObj.displayAll();
        Dog dog = new Dog("Yorke",8,25,2,4,1,32,"long silky");
        dog.eat();
        //dog.walk();
        System.out.println("\n\n");

        dog.run();
    }
}
