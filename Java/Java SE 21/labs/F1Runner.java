package labs;

public class F1Runner {
    public static void main(String[] args) {
        byte a = 10, b = 10;
        // byte c = a + b; -> error: incompatible types: possible lossy conversion from
        // int to byte
        int c = a + b;
        int d = 100;
        float f = d; // Auto Cast
        float i = 25;
        // int j = i; -> possible lossy conversion from float to int
        int j = (int)i;
        byte k = 127;
        System.out.println(k); // 127
        k++;
        System.out.println(k); // -128 -> Overflow
        char l = 'a';
        System.out.println(l); // a
        l++;
        System.out.println(l); // b -> Arithmetic operations work on character codes

    }
}