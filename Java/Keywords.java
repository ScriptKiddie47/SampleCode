import java.awt.desktop.ScreenSleepEvent;

public class Keywords {
    public static void main(String args[]) {

        boolean gameOver = false;
        int score = 50000;
        int levelCompleted = 5;
        int bonus = 100;

        if (score == 5000) {
            System.out.println("Your score was 5000");
            System.out.println("This was executed");
        }
        hello(gameOver); //Call to Static method
        Keywords obj = new Keywords();
        obj.bye();

        displayHighScorePosition("niko", calculateHighScorePosition(1500));
        displayHighScorePosition("Faze", calculateHighScorePosition(900));
        displayHighScorePosition("Rain", calculateHighScorePosition(400));
        displayHighScorePosition("niko", calculateHighScorePosition(50));

        display();

    }

    public static void hello(boolean value) {
        System.out.println(value);
    }

    public void bye() {
        System.out.println("Bye");
    }

    // Create a method called displayHighScorePosition
    // it should a players name as a parameter, and a 2nd parameter as a position in the high score table
    // You should display the players name along with a message like " managed to get into position " and the
    // position they got and a further message " on the high score table".
    //
    // Create a 2nd method called calculateHighScorePosition
    // it should be sent one argument only, the player score
    // it should return an in
    // the return data should be
    // 1 if the score is >=1000
    // 2 if the score is >=500 and < 1000
    // 3 if the score is >=100 and < 500
    // 4 in all other cases
    // call both methods and display the results of the following
    // a score of 1500, 900, 400 and 50
    //


    public static void displayHighScorePosition(String name, int posi) {
        System.out.println(name + " managed to get the position:" + posi + " on the high score table");

    }

    public static int calculateHighScorePosition(int playerScore) {
        if (playerScore >= 1000) {
            return 1;
        } else if (playerScore >= 500 && playerScore < 1000) {
            return 2;
        } else if (playerScore >= 100 && playerScore < 500) {
            return 3;
        } else {
            return 4;
        }
    }

    public static boolean shouldWakeUp(boolean barking, int hourOfDay) {

        if (barking == true) {
            if ((hourOfDay < 8 && hourOfDay > 0) || hourOfDay > 22) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static void display() {
        System.out.println(shouldWakeUp(true, 1));
        System.out.println(shouldWakeUp(false, 2));
        System.out.println(shouldWakeUp(true, 8));
        System.out.println(shouldWakeUp(true, -1));

        System.out.println("\n\n");

        System.out.println(areEqualByThreeDecimalPlaces(-3.1756, -3.175));
        System.out.println(areEqualByThreeDecimalPlaces(3.175, 3.176));
        System.out.println(areEqualByThreeDecimalPlaces(3.0, 3.0));
        System.out.println(areEqualByThreeDecimalPlaces(-3.123, 3.123));

        int x = calculateScore("Ritam",25);
        int y = calculateScore(25);
        int z = calculateScore();

        System.out.println(HELLO_MAMA);
    }

    public static boolean areEqualByThreeDecimalPlaces(double num1, double num2) {
        if ((int) (num1 * 1000) == (int) (num2 * 1000)) {
            return true;
        } else {
            return false;
        }
    }
    //Method Overloading
    public static int calculateScore (String playerName,int score){
        System.out.println("Player:" + playerName+"score"+score);
        return score*100;
    }
    public static int calculateScore(int score){
        System.out.println("Score:"+score);
        return score*100;
    }
    public static int calculateScore(){
         System.out.println("Empty Method");
         return 0;
    }
    private static final String HELLO_MAMA = "Hello Mama";
}