package com.scripter;

public class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println(ThreadColor.ANSI_CYAN+"My Runnable Thread");
    }
}
