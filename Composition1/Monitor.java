package com.scriptKiddie;

import javax.print.attribute.ResolutionSyntax;

public class Monitor {
    private String model;
    private String manufacturer;
    private int size;
    private Resolution nativeRes; //Composition

    //Resolution is a path of the monitor.

    public Monitor(String model, String manufacturer, int size, Resolution nativeRes) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.size = size;
        this.nativeRes = nativeRes;
    }
    public void drawPixelAt(int x,int y,String color){
        System.out.println("Drawing pixel at x:"+x+" y:"+y+" color:"+color);
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Resolution getNativeRes() {
        return nativeRes;
    }

    public void setNativeRes(Resolution nativeRes) {
        this.nativeRes = nativeRes;
    }
}
