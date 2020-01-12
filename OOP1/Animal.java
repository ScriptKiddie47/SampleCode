/*
 * Inheritance Example Head Class
 */


package com.scriptKiddie;

public class Animal {
    private String name;
    private int brain;
    private int body;
    private int size;
    private int weight;

    public Animal() {
        System.out.println("Animal Default Constructor");
    }

    public Animal(String name, int brain, int body, int size, int weight) {
        System.out.println("Animal Customized Constructor");
        this.name = name;
        this.brain = brain;
        this.body = body;
        this.size = size;
        this.weight = weight;
    }

    public void eat(){
        System.out.println("Animal.eat() Called");
    }
    public void move(int speed){
        System.out.println("Animal.move() Called");
        System.out.println("Animal is moving at speeds:"+speed);
    }

    public void displayAll() {
        System.out.println("name:"+name);
        System.out.println("brain:"+brain);
        System.out.println("body:"+body);
        System.out.println("size:"+size);
        System.out.println("weight:"+weight);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBrain() {
        return brain;
    }

    public void setBrain(int brain) {
        this.brain = brain;
    }

    public int getBody() {
        return body;
    }

    public void setBody(int body) {
        this.body = body;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }



}
