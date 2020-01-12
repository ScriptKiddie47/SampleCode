package com.scriptKiddie;

public class VipCustomer {
    private String name;
    private double creditLimit;
    private String emailAddress;

    public VipCustomer() {
        this("John Doe", 2500.03, "johnDoe@xyz.com");
    }

    public VipCustomer(String name, double creditLimit) {
        this(name, creditLimit, "Default@xyz");
    }

    public VipCustomer(String name, double creditLimit, String emailAddress) {
        this.name = name;
        this.creditLimit = creditLimit;
        this.emailAddress = emailAddress;
    }
    public void displayAll(){
        System.out.println("name:"+name);
        System.out.println("creditLimit:"+creditLimit);
        System.out.println("emailAddress:"+emailAddress);

    }

}


