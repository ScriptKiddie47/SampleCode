package com.scriptKiddie;

public class Motherboard {
    private String model;
    private String manufracturer;
    private int ramSlots;
    private int cardSlots;
    private String bois;

    public Motherboard(String model, String manufracturer, int ramSlots, int cardSlots, String bois) {
        this.model = model;
        this.manufracturer = manufracturer;
        this.ramSlots = ramSlots;
        this.cardSlots = cardSlots;
        this.bois = bois;
    }

    public void loadProgram(String programName){
        System.out.println("Program "+programName+"is now loading");
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufracturer() {
        return manufracturer;
    }

    public void setManufracturer(String manufracturer) {
        this.manufracturer = manufracturer;
    }

    public int getRamSlots() {
        return ramSlots;
    }

    public void setRamSlots(int ramSlots) {
        this.ramSlots = ramSlots;
    }

    public int getCardSlots() {
        return cardSlots;
    }

    public void setCardSlots(int cardSlots) {
        this.cardSlots = cardSlots;
    }

    public String getBois() {
        return bois;
    }

    public void setBois(String bois) {
        this.bois = bois;
    }
}
