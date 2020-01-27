package com.scriptKiddie;

public class Main {
    public static void main(String args[]){
        HouseColor housecolor = new HouseColor("red","white");
        Furniture furniture = new Furniture(24,"Green");

        House house1 = new House(housecolor,furniture);
        System.out.println(house1.getHouseColor().getColor1());
    }
}
