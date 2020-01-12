package com.scriptKiddie;

public class BankAccount {
    private int accountNumber;
    private double balance;
    private String customerName;
    private String email;
    private int phoneNumber;

    public BankAccount() {
        this(56782,55656.25,"Mr.Default","default@gmail.com",990344696);
    }

    public BankAccount(int accountNumber, double balance, String customerName, String email, int phoneNumber) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void depositFund(double funds) {
        this.balance = this.balance + funds;
        System.out.println("Current Balance After deposit:" + this.balance);
    }

    public void withdrawFunds(double funds) {
        if (funds > this.balance) {
            System.out.println("Not Enough Money");
        } else {
            this.balance = this.balance - funds;
            System.out.println("Current Balance After Deduction:" + this.balance);
        }
    }

    public void displayAll() {

        System.out.println("accountNumber:" + accountNumber);
        System.out.println("balance:" + balance);
        System.out.println("customerName:" + customerName);
        System.out.println("email:" + email);
        System.out.println("phoneNumber:" + phoneNumber);

    }
}
