package com.scripter;

public class ClientMain {
    //Thread
    public static void main(String[] args) {
        System.out.println("Main Thread --> " + "Hello From the main thread Start Game");
        Thread anotherThread = new AnotherThread();
        anotherThread.setName("Another Thread -- > ");
        anotherThread.start();

        new Thread() {
            @Override
            public void run() {
                System.out.println("Anonymous Thread --> " + "Hello");
            }
        }.start();

        Thread myRunnableThread = new Thread(new MyRunnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " Says Hello");
                try {
                    anotherThread.join();
                    System.out.println(Thread.currentThread().getName() + "Another thread " +
                            "terminated , so I am running again");
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + "I couldn't wait after all" +
                            " I was interrupted.");
                }
                System.out.println(Thread.currentThread().getName() + " Says Stuck with" +
                        " a thread wait");
            }
        });
        myRunnableThread.setName("MyRunnable Anonymous Thread ---> ");
        myRunnableThread.start();
        System.out.println("Main Thread --> " + "Hello From the main thread End Game");
    }

}
