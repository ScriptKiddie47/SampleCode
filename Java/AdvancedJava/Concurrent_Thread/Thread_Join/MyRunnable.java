package com.scripter;

public class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"Say My Runnable Thread");
    }
}
