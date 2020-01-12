/*
 * Inheritance Sub Class
 * */

package com.scriptKiddie;

public class Dog extends Animal {
    /*public Dog() {
        System.out.println("Dog Default Constructor");
    }*/

    private int eyes;
    private int legs;
    private int tail;
    private int teeth;
    private String coat;


    //Calls the constructor of the class we are extending from
    public Dog(String name, int size, int weight, int eyes, int legs, int tail, int teeth, String coat) {
        super(name, 1, 1, size, weight);
        this.eyes = eyes;
        this.legs = legs;
        this.tail = tail;
        this.teeth = teeth;
        this.coat = coat;
    }

    private void chew() {
        System.out.println("Dog.chew() Called");
    }

    @Override
    public void eat() {
        System.out.println("Dog.eat() Called");
        chew();
        super.eat();
    }

    public void walk() {
        System.out.println("Dog.walk() Called");
        move(5);
    }
    public void run() {
        System.out.println("Dog.run() Called");
        super.move(10);
    }

    @Override
    public void move(int move){
        System.out.println("Dog.move():");
        System.out.println("Speed:"+move);
    }
}
