# Compiling a Java Program

1. The `-classpath` or `-cp` parameter points to location of other classes that may be required to compile your code.
1. The `-d` parameter points to path to store the compilation result
1. Finally provide the source file.

1. Folder Structure :

    ```ps
    `-- labs
        `-- F1Runner.java
    ```

1. Java Code

    ```java
    package labs;
    public class F1Runner {
        public static void main(String[] args) {
            System.out.println("Hello" + args[0]);
        }
    }
    ```

1. Command line command to Compile :

    ```ps
    $ javac -d ./classes ./labs/F1Runner.java
    $ tree
    .
    |-- classes
    |   `-- labs
    |       `-- F1Runner.class
    `-- labs
        `-- F1Runner.java
    ```


# Executing a java program

1. The `-classpath` or `-cp` parameter points folder where our classes are located.
1. Specify fully qualified class name. Use package prefix. Do not use .class extension
1. Provide a space separated list of parameters after the class name.

    ```ps
    $ java -cp ./classes labs.F1Runner "Syndicate"
    HelloSyndicate
    ```


# Comments and Documentation

1. `javadoc`

## Code Snippet in Documentation

1. Java 18 Feature. 

#### Example Here