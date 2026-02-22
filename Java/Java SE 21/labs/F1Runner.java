package labs;

public class F1Runner {
    public static void main(String[] args) {
        int a = 5;
        int b = 2;
        boolean c = (a > b) ^ ( b == 2);
        boolean d = (a > b) || ( b == 2);
        System.out.println(c); // False
        System.out.println(d); // True


    }
}
