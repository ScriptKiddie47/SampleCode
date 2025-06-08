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