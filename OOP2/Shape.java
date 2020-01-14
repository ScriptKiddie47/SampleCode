package com.scriptKiddie;

public class Shape {
    private int x;
    private int y;


    public Shape(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Rect extends Shape {
    private int width;
    private int height;

    //1st Constructor
    public Rect(int x,int y){
        this(x,y,0,0);
    }
    public Rect(int x,int y,int width,int height){
        super(x,y);
        this.width = width;
        this.height = height;
    }
}
