package com.scriptKiddie;

public class Furniture {

    private int bedSize;
    private String bedColor;

    public Furniture(int bedSize, String bedColor) {
        this.bedSize = bedSize;
        this.bedColor = bedColor;
    }

    public int getBedSize() {
        return bedSize;
    }

    public String getBedColor() {
        return bedColor;
    }
}
