import java.util.Scanner;

public class Control_Flow {
    public static void main(String args[]) {

        int s = 3;
        switch (s) {
            case 1:
                System.out.println(s);
            case 2:
                System.out.println(s);
            case 3:
                System.out.println(s);
            default:
                System.out.println("Default");
        }

        String numberAsString = "2018";
        System.out.println("numberAsString:" + numberAsString);

        //Integer is a class
        //Wrapper class for the primitive type int
        //Wrapper class integer contains some cool static methods like parseInt
        int num = Integer.parseInt(numberAsString);
        System.out.println(num);

        numberAsString += 1;
        num += 1;

        System.out.println("numberAsString:" + numberAsString);
        System.out.println(num);

        /******************************************************************/

        String numberAsStringD = "2018.18";
        System.out.println("numberAsStringD:" + numberAsStringD);

        //Integer is a class
        //Wrapper class for the primitive type int
        //Wrapper class integer contains some cool static methods like parseInt
        double numD = Double.parseDouble(numberAsStringD);
        System.out.println(numD);

        numberAsStringD += 1;
        numD += 1;

        System.out.println("numberAsStringD:" + numberAsStringD);
        System.out.println(numD);

        //Reading User Input
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Your date of Birth:");
        boolean hasNextInt = scanner.hasNextInt(); //Checks to see
        // if the next input entered is a number
        if (hasNextInt == true) {
            int dob = scanner.nextInt();
            //To Handle the Enter Key Issue,we insert the nextLine()
            scanner.nextLine();

            System.out.println("Enter Your Name:");
            String name = scanner.nextLine();
            //Lets close the scanner , cause it releases underlying memory it using

            System.out.println("Your name is:" + name + " DOB:" + dob);
        }
        scanner.close();
    }

}
