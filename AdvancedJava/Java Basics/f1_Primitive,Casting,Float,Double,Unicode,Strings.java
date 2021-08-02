package com.revisted.main;

public class ClientMain {
    public static void main(String[] args) {

        //Java Primitive Types
        int myValue = 1000;
        int minIntValue = Integer.MIN_VALUE;
        int maxIntValue = Integer.MAX_VALUE;

        System.out.println("Integer.MIN_VALUE:" + minIntValue); // -2147483648
        System.out.println("Integer.MAX_VALUE:" + maxIntValue); // 2147483647

        //Wrapper Classes
        //The wrapper class in Java provides the mechanism to convert primitive
        // into object and object into primitive

        //Use of Wrapper classes in Java
        //Change the value in Method: Java supports only call by value. So, if we pass a primitive value, it will not change the original value. But, if we convert the primitive value in an object, it will change the original value.
        //Serialization: We need to convert the objects into streams to perform the serialization. If we have a primitive value, we can convert it in objects through the wrapper classes.
        //Synchronization: Java synchronization works with objects in Multithreading.
        //java.util package: The java.util package provides the utility classes to deal with objects.
        //Collection Framework: Java collection framework works with objects only. All classes of the collection framework (ArrayList, LinkedList, Vector, HashSet, LinkedHashSet, TreeSet, PriorityQueue, ArrayDeque, etc.) deal with objects only.

        //Usage of UnderScore [ Unique Case ] , JAVA 7 or Higher

        int myValueInUnderScore = 100_000;
        System.out.println("print 100_000 : " + myValueInUnderScore); //100000

        System.out.println("------------------------------------------------");

        byte minByteValue = Byte.MIN_VALUE;
        byte maxByteValue = Byte.MAX_VALUE;

        System.out.println("Byte.MIN_VALUE" + minByteValue);
        System.out.println("Byte.MAX_VALUE" + maxByteValue);

        System.out.println("------------------------------------------------");

        long myLongValue = 100L; //L forces Java to treat that as a long
        System.out.println("Long.MIN_VALUE:" + Long.MIN_VALUE); // -9223372036854775808
        System.out.println("Long.MAX_VALUE:" + Long.MAX_VALUE); // 9223372036854775807

        long veryLongNumber = 2147483647;
        long veryLongNumber2 = 2147483648L;

        System.out.println("------------------------------------------------");

        //Casting
        //ERROR : byte myNewByteValue = (maxByteValue / 2);
        // CAUSE : Default Whole Number used by Java is an Int
        //FIX Using Type Casting
        byte myNewByteValue = (byte) (maxByteValue / 2);
        System.out.println("myNewByteValue:" + myNewByteValue); // 63

        System.out.println("------------------------------------------------");

        //FLOAT AND DOUBLE
        //ERROR :  float myFloatValue = 5.25;
        //FIX 1 :  float myFloatValue = (float)5.25;
        //FIX 2 :  float myFloatValue = 5.25f;

        float myFloatValue = 5.25f;
        double myDoubleValue = 5.25d;

        System.out.println("myFloatValue:" + myFloatValue); //5.25
        System.out.println("myDoubleValue:" + myDoubleValue); //5.25

        System.out.println("------------------------------------------------");

        //DIVISION
        float myFloatValue2 = 5f / 2f;
        double myDoubleValue2 = 5d / 2d;

        System.out.println("myFloatValue2:" + myFloatValue2); //2.5
        System.out.println("myDoubleValue2:" + myDoubleValue2); //2.5

        System.out.println("------------------------------------------------");

        //DIVISION 2
        float myFloatValue3 = 5f/3f;
        double myDoubleValue3 = 5d/3d;

        System.out.println("myFloatValue3:"+myFloatValue3); //1.6666666
        System.out.println("myDoubleValue3:"+myDoubleValue3); //1.6666666666666667

        // PI , Under score
        double pi = 3.14159;
        double anotherNumber = 3.123_456;
        System.out.println("pi:"+pi); //3.14159
        System.out.println("anotherNumber:"+anotherNumber); //3.123456

        System.out.println("------------------------------------------------");

        //CHAR & UNICODE
        char myChar = 'D';
        char myUnicode1 = '\u0996';

        System.out.println("myChar:"+myChar); // D
        System.out.println("myUnicode1:"+myUnicode1); // খ

        System.out.println("------------------------------------------------");

        //Strings
        //String in Java is Immutable
        String myString1  = "Hello Ji";

        String number1 = "250.55";
        int number2 = 50;
        String number3 = number1 + number2;
        System.out.println(number3); //250.5550

    }
}
