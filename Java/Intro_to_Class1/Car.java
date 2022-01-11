package com.scriptKiddie;

public class Car {

    public int doors; //1 public method , not recommended
    private int wheels;
    private String model;
    private String engine;
    private String color;

    public int getWheels() {
        return wheels;
    }

    public void setWheels(int wheels) {
        this.wheels = wheels;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        String validModel = model.toLowerCase();
        if (validModel.equals("cab") || validModel.equals("caa")) {
            this.model = model;
        } else {
            this.model = "Unknown";
        }
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }
}
