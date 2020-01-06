public class Main {

    public static void main(String[] args) {
        char myChar = '\u01DA';
        System.out.println(myChar); //ǚ

        //byte
        //short
        //int
        //long
        //short
        //double
        //boolean
        //char

        String myString = "This is a String";
        System.out.println("myString:"+myString);

        myString = myString + ", and even more";
        System.out.println("myString:"+myString);

        myString = myString + " \u00A9 2019";
        System.out.println("myString: "+myString);

        String numberString = "250.55";
        System.out.println("numberString:"+numberString);

        numberString = numberString + "6985";
        System.out.println("numberString:"+numberString);

        String lastString = "10";
        int myInt = 50;
        lastString = lastString + myInt;
        System.out.println("lastString:"+lastString);


        double myDouble = 365.25d;
        lastString = lastString + myDouble;
        System.out.println("lastString:"+lastString);

        //if-then
        boolean isAlien = true;
        if(isAlien == false)
            System.out.println("Its is not a Alien!");
            System.out.println("OMG"); //Fires No Code Block

        int topSCore = 100;
        if(topSCore != 100){
            System.out.println("You got the Top Score");
        }

        int newV = 5;
        if(newV == 10){
            System.out.print("1");
        }

        boolean isCar = false;

        if (isCar = false){
            System.out.println("This is not supposed to happen");
        }

        boolean wasCar = isCar?true:false;
        System.out.println(wasCar);

        int ageOfClient = 20;
        boolean is18Over = (ageOfClient > 18)?true:false;
        System.out.println(is18Over);

        double myValue1 = 20;
        double myValue2 = 80;
        double sumMul = (20+80)*100;
        System.out.println("sumMul:"+sumMul);
        //double rem = sumMul % 40;
        boolean check = ((20+80)*100)%40 == 0?true:false;
        System.out.println((20+80*100)%40);
        System.out.println("Output:"+check);
        if(!check){
            System.out.println("Got Some Remainder");
        }



    }
}
