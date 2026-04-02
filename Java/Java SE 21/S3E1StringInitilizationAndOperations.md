# String 

1. JVM automatically `intern` string objects & maintain only a single copy of each string literal.
1. `new` operator discards the `intern` process.
1. We can intern explicitly as well. `c.intern()`

    ```java
    String a = "H";
    String b = "H";
    String c = new String("H");
    String d = c.intern();
    System.out.println(a==b); // true
    System.out.println(c==b); // false
    System.out.println(c==d); // false
    System.out.println(a==d); // true
    ```
1. Pay Attention here : `System.out.println(c==d); // false` 
1. `+` is both a string concatenation as well as an arithmetic operator.

```java
String  s = "";
s = 1+1+"u"; //2u
s = "u" + 1 + 1; //u11 
s = "u" + ( 1 + 1); //u2 
```