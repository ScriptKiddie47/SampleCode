package com.scriptKiddie;

public class mainClass {
    public static void main(String args[]) {
        Car porshe = new Car();
        Car holden = new Car(); //Holden is a australian company
        porshe.doors = 5;
        porshe.setModel("911");
        System.out.println("Porshe Doors:" + porshe.getDoors());
        System.out.println("Porshe Model:" + porshe.getModel());
    }
}
