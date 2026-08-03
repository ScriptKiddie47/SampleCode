import java.util.ArrayList;
import java.util.List;

public class Code {
    public static void main(String[] args) {
        String str = "abc";

        // "abc,acb,bac,bca,cab,cba"

        char[] c = str.toCharArray();

        // input {1,2,3,4} output {24,12,8,6}

        int[] ar1 = new int[] { 1, 2, 3, 4 };

        List<Integer> mult = new ArrayList<>();
        for (int j = 0; j < ar1.length; j++) {

            int m = 1;
            for (int i = 0; i < ar1.length; i++) {
                
                if (i == j) {
                    // Do Nothing
                } else {
                    m = m * ar1[i];
                }
            }
            mult.add(m);
        }
        System.out.println(mult);

    }
}