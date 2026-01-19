# Arithmetic Operators and TypeCasting

1. <span style="color:blue">Smaller types are automatically caster to bigger types</span>
1. byte->short->char->int->long->float->double
1. A bigger type cannot be assigned to a smaller type without explicit <span style="color:red">type casting</span>.
1. When casting a bigger value to a smaller value , beware of <span style="color:red">overflow</span>.
1. <span style="color:blue">Resulting type of arithmetic operation on smaller type than int is int. Otherwise the result is of the largest participant.</span>


    ```java
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
    System.out.println(l); // b -> Increased due to arithmetic operations
    ```
1. Arithmetic operations work on character codes
1. `Math.round()` always round to the whole number.
1. But what if I want it be upto say 2 decimal places 

    ```java
    double c = (double)11/3;
    System.out.println(c); // 3.6666666666666665
    System.out.println(Math.round(c)); // 4
    System.out.println((double)Math.round(c*100)/100.0); // 3.67
    ```

## Binary Number Representation in Java

1. All java numerics are signed ( ie: +ve & -ve values)
1. Change the sign ( pos & neg ) is done by inverting all the bits then adding 1 to the result.

    ```java
    int a = 5;
    int b = ~a;
    int c = ~a + 1;
    System.out.println(a); // 5 
    System.out.println(b); // -6
    System.out.println(c); // -5
    ```

1. 