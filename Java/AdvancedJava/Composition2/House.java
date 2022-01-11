package com.scriptKiddie;

public class House {
    private HouseColor houseColor;
    private Furniture furniture;

    public House(HouseColor houseColor, Furniture furniture) {
        this.houseColor = houseColor;
        this.furniture = furniture;
    }

    public HouseColor getHouseColor() {
        return houseColor;
    }

    public Furniture getFurniture() {
        return furniture;
    }
}
