# String 

1. String is a class (not a primitive) that represents a sequence of characters. String can be interned, thus present a single copy of a string literal, but interments does not always happen, for example if String is created with a new operator. Also, String objects are immutable, which means it is not possible to modify text value stored in a String object.
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

## String operations

1. Substring - Use it effectively

    ```java
    String  s = "Hello";
    System.out.println(s.substring(0,1)); // H
    System.out.println(s.substring(1))); // ello
    ```
1. If we look for an index that doesn't exist we get `java.lang.StringIndexOutOfBoundsException`
1. `indexOf('o')` or  `indexOf('o',2)` -> Index of 'o' or Index of 'o' after 2 indixes
1. `StringBuilder builder = new StringBuilder();` should be used to constantly updating string. Default capacity is 16 & it auto expands
1. StringBuilder objects are not thread-safe

```java
StringBuilder builder = new StringBuilder();
builder.append("abc");
builder.insert(0, 'a'); // aabc -> Insert is interesting
```