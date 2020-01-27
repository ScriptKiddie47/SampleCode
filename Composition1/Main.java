package com.scriptKiddie;

public class Main {
    public static void main(String args[]){
        Dimensions dimensions = new Dimensions(20,20,5);
        Case theCase = new Case("2208","Dell","2480",dimensions);

        Monitor theMonitor = new Monitor("27Inch Beach","Acer",27,new Resolution(1980,1080));
        Motherboard motherboard = new Motherboard("z84 ","Asus",4,4,"v20.4");

        PC thePC = new PC(theCase,theMonitor,motherboard);
        thePC.getMonitor().drawPixelAt(1500,1200,"red");
        thePC.getMotherboard().loadProgram("Windows 1.0");
        thePC.getTheCase().pressPowerButton();
    }
}
