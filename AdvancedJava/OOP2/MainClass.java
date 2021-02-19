package com.scriptKiddie;

public class MainClass {
    public static void main(String args[]) {
        House h1 = new House("Red");
        House h2 = h1;

        System.out.println("h1 Color:" + h1.getColor());
        System.out.println("h2 Color:" + h2.getColor());

        h2.setColor("Green");
        System.out.println("\n");
        System.out.println("h1 Color:" + h1.getColor());
        System.out.println("h2 Color:" + h2.getColor());
        System.out.println("\n");

        House h3 = new House("Yellow");
        h2 = h3;
        System.out.println("h3 Color:" + h3.getColor());
        System.out.println("h2 Color:" + h2.getColor());
        System.out.println("h1 Color:" + h1.getColor());

        System.out.println("\n");
        StaticClass.printSum(5,5);
        PrintHello();

        Dog rex = new Dog("rex");
        Dog fluffy = new Dog("fluffy");

        rex.printName();
        fluffy.printName();

        //Coding at the Speed of light
    }

    public static void PrintHello(){
        System.out.println("Hello");
    }

}