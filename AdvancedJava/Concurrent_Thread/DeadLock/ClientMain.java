package com.scripter;

public class ClientMain {
    public static void main(String[] args) {
        Message message = new Message();
        System.out.println("Main Thread Starts --> ");
        (new Thread(new Writer(message))).start();
        (new Thread(new Reader(message))).start();
        System.out.println("Main Thread Ends --> ");
    }
}
