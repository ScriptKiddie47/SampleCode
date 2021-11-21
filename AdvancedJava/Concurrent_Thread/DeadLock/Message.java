package com.scripter;

import java.util.Random;

public class Message {
    private String message;
    private boolean empty = true;

    public Message() {
        System.out.println("Message Object Initiated");
    }

    public synchronized String read() {
        while (empty) {
            System.out.println("Class : Message : While empty -- > true");
        }
        empty = true;
        return message;
    }

    public synchronized void write(String message) {
        while (!empty) {
            System.out.println("Class : Message : While empty -- > false ");
        }
        empty = false;
        this.message = message;
    }
}

class Writer implements Runnable {
    private Message message;

    public Writer(Message message) {
        this.message = message;
        System.out.println("Writer Object Initiated");
    }

    @Override
    public void run() {
        String[] messages = {
                "Humpty Dumpty Sat on a Wall",
                "Humpty Dumpty had a great fall",
                "All the king's horses and all the king's men",
                "Couldn't put Humpty together again"
        };

        Random random = new Random();
        for (int i = 0; i < messages.length; i++) {
            message.write(messages[i]);
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {

            }
        }
    }
}

class Reader implements Runnable {
    private Message message;

    public Reader(Message message) {
        this.message = message;
        System.out.println("Reader Object Initiated");
    }

    @Override
    public void run() {
        Random random = new Random();
        for (String latestMessage = message.read(); !latestMessage.equals("Finished"); latestMessage = message.read()) {
            System.out.println(latestMessage);
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {

            }
        }
    }
}