package com.scripter;

public class ClientMain {
    //Thread
    public static void main(String[] args){
        System.out.println(ThreadColor.ANSI_RED + "Hello From the main thread");
        Thread anotherThread = new AnotherThread();
        anotherThread.setName("=== Another Thread===");
        anotherThread.start();

        new Thread() {
            @Override
            public void run() {
                System.out.println(ThreadColor.ANSI_GREEN + "hello from the anonymous " +
                        "class1");
            }
        }.start();

        Thread myRunnableThread = new Thread(new MyRunnable());
        myRunnableThread.start();
        anotherThread.interrupt();
        System.out.println(ThreadColor.ANSI_RED + "Hello From the main thread");
    }

}
