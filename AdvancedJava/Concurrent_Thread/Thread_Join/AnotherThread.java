package com.scripter;

public class AnotherThread extends Thread {
    @Override
    public void run() {
        System.out.println(currentThread().getName() + "Says Hello");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(currentThread().getName() + "Says Another thread woke me" +
                    " up");
            return;
        }

        System.out.println(currentThread().getName() + "Says Three seconds have passed " +
                "and" +
                " I am " +
                "awake");
    }
}
