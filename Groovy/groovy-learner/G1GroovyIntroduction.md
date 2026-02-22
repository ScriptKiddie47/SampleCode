# Groovy Setup

1. Download Binary & add it to path

    ```ps
    $ groovy -v
    Groovy Version: 4.0.12 JVM: 17.0.0.1 Vendor: Oracle Corporation OS: Linux
    ```

## Hello World

File Name : F1GroovyHello.groovy

```groovy
class F1GroovyHello {
    static void main(String[] args) {
        println('Hello');
    }
}
```
Run it

```ps
$ groovy F1GroovyHello.groovy 
Hello
```

## Define Varibales

1. Dynamically Typed

    ```groovy
    class F1GroovyHello {
        static void main(String[] args) {
            println('Hello');
            def age = "Dog";
            println(age); // Dog
            age = 20
            println(age); // 20
        }
    }
    ```
## Math Ops

1. Order of Operations : BOADMAS
1. Grooyv is very accurate with decimal calculations

    ```groovy
    println(5+4); //9
    println(5-4); //1 
    println(5*4); //20
    println(5.intdiv(4)); //1
    println(5%4); //1

    println(5.2.plus(4.4)); // 9.6 
    println(5.2.minus(4.4)); // 0.8
    println(5.multiply(4.4)); // 22.0
    println(5 / 4); // 1.25

    println("Biggest Integer : " + Integer.MAX_VALUE); // Biggest Integer : 2147483647
    println(Math.abs(-2.45)); // 2.45
    ```

## Strings


1. Mostly same as Java
1. Double Quotes does String interpolation

    ```groovy
    def name = "Derek";
    println("I am ${name}"); // I am Derek
    println('I am ${name}'); // I am ${name}
    ```

1. Multiline String

    ```groovy
    def name = '''I am a 
    multiline  String'''
    println(name)
    ```
1. The output is as expected

    ```ps
    $ groovy F1GroovyHello.groovy
    I am a 
            multiline  String
    ```
1. Get Index,Sclice,Equality

    ```groovy
    def name = "Derek";
    println(name[3]) // e
    println(name.indexOf('k')) // 4
    println(name[0..2]); // Der
    println(name[0,2,4]); // Drk
    println('Derek'.equals('Derek')) // true
    println('Derek'.equals('derek')) // false
    println('Derek'.equalsIgnoreCase('derek')) // true
    ```

1. String to List

    ```groovy
    println(name.split(' ')); // [This, should, be, fun]
    println(name.toList()); // [T, h, i, s,  , s, h, o, u, l, d,  , b, e,  , f, u, n]
    ```

1. Compare String <=>

    ```groovy
    println('Ant' <=> 'Banana') // -1
    println('Ant' <=> 'Ant') // -0
    println('Banana' <=> 'Ant' ) // 1
    ```
1. More String functions


    ```groovy
    def randString = "Random";
    println("A ${randString} string");
    printf("A %s string \n",randString);
    printf("%-10s %d %.2f %10s string \n",['Stuff',10,1.234,'Random']); // Stuff      10 1.23     Random string
    // %-10s puts padding on the right side
    // %10s puts padding on the left side
    ```
    