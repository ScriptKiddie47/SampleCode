# Java Classes

1. `Product p1 = new Product()`
1. The `new` operator creates an object
1. `p1` -> A reference points to a object in memory.


## Instance Variables

1. Variables can be initialized ( Assigned a defalt value)
1. Uninitialized primitives are defualted to `0` except `boolean`
1. Uninitialized object references are defaulted to `null`.

    ```java
    public class SingleScript {
        public static void main(String[] args) {
            Product product = new Product();
            System.out.println(product.id); // 0
            System.out.println(product.idInteger); // null
        }
    }

    class Product{
        int id;
        Integer idInteger;
    }
    ```

## Local Variable Type Inference

1. `var v1 = "Hello";` 
1. Infer types of local variables with initializers. No need to explicity describe.


# Final

1. `final` marks a variable as constant. Once initialized Can't change.
1. Instance variable marked as `final` must be initialized immediately ( instance initializer ) or via constructors
1. Local variables & parameters can also be marked as final.

## Final Class & Methods

1. Class cannot extend a class that is marked by `final`.
1. A sublcass cannot override a superclass method that is marked by `final`. Attempting to do so will result in compilation error.

# Static

1. Shared by all instances of a class

    ```java
    public class SingleScript {
        public static void main(String[] args) {
            Product p1 = new Product();
            Product p2 = new Product();
            p1.counter = 10; // This is not recommended Product.counter
            System.out.println(p2.counter); // This is not recommended Product.counter
        
        }
    }

    class Product{
        static int counter;
    }
    ```

1. Attempt to access current instance methods or variables from the static context will result in compiler error. Instance variables and methods are not accessible through the static context.

    ```java
    class Product{
        String v1 = "Hello";
        public static void printHello(){
            System.out.println(v1);
        }
    }
    ```
    ```ps
    $ java SingleScript.java
    SingleScript.java:10: error: non-static variable v1 cannot be referenced from a static context
            System.out.println(v1);
                            ^
    1 error
    error: compilation failed
    ```

1. All operation of `Math` class is static.
1. `Factory methods` are static that create and return new instance.
1. `static import` enables referencing static variables and methods of another class as if they are in the class.

### Static Initializer

1. Runs once before any other operation

    ```java
    public class SingleScript {
        public static void main(String[] args) {
            System.out.println(Product.counter); // 10
        }
    }

    class Product {
        static int counter;
        static {
            counter = 10;
        }
    }
    ```

### Combining Static and FInal

1. Simple way of defining globally visible constant
1. Encapsulation is not required because the value is readonly

    ```java
    public class SingleScript {
        public static void main(String[] args) {
            System.out.println(Product.MAX_EXPIRY_PERIOD); // 5
        }
    }

    class Product {
        public static final int MAX_EXPIRY_PERIOD = 5;
    }
    ```

# Overloading

1. Two or more methods, within the same class, have the same name. They must have different number,different types or different order of parameters.
1. Method CANNOT be overloaded:
    1. Using different parameter names
    1. Different return types.
1. Constructor overloading can achieved using `this`.

```java
class Product {
    public Product(String name,String price){
        this(name);
    }
    public Product(String name){
        
    }
}
```

### Vararg ( Variable number of arguments )

1. The vararg parameters is used as an array.

```java
public class SingleScript {
    public static void main(String[] args) {
        Product p = new Product();
        p.printMultiple(10,20);
    }
}

class Product {
    void printMultiple(int... values){
        for(int i : values){
            System.out.println(i); // 10,20
        }
    }
}
```

# Access Modifiers

1. `public` - Visible to any class
2. `protected` -  Visible to classes that are in the same package and to subclasses
3. `<default>` - Visible to classes in the same package only
4. `private` - Visible within the same class only


# Encapsulation

1. Ability of protecting data inside a given class.

    ```java
    class Product {
        private String name;
    }
    ```

## Instance Initializer

1. Triggers before the invocation of a constructor.

    ```java
    public class SingleScript {
        public static void main(String[] args) {
            Product p1 = new Product();
            System.out.println(p1.getName());
        }
    }

    class Product {
        private String name;
        {
            System.out.println("Triggered First");
            name = "Bala";
        }
        Product(){
            System.out.println("Triggered Second");
        }
        public String getName() {
            return name;
        }
    }
    ```
    Output:

    ```ps
    $ java SingleScript.java
    Triggered First
    Triggered Second
    Bala
    ```

## Enums

1. Enum values are implicitly `public`,`static` & `final`.
1. Enum values are enum type.
1. Enum constants cannot have access modifiers.

    ```java
    public enum Condition{
        HOT,WARM,COLD;
    }
    ```
### Parameter Passing

1. Just like local method variables,parameters are stored in stack.
1. Passing parameters means copying stack values:
    1. A copy of an object reference value
    1. A copy of primitive value


# Extending Classes

1. Classes from a hierarchy decending from the `java.lang.Object` class. Ultimate parent of all class.
    1. Object class contains toString,equals,hashCode,clone..methods. `hashCode()` method generates int hash value for an object
1. Parent Class is SuperClass. Child class is known as subclass.
1. A class can only have one immediate parent, as `multiple inheritance is not allowed in Java`.

    ```java
    class A {}
    class B extends A{}
    class C extends A{}
    class D extends A,B{} // Not Allowed
    ```

# Inheritance ( extends )

1. Reuse generic superclass behaviours and state in subclass.

# Object References 

1. Object references can be of generic or specific types.

    ```java
    public class SingleScript {
        public static void main(String[] args) {
            Object o1 = new Product();
            Product p1 = new Food();
            Food f1 = new Food();
            System.out.println(o1.product_name); // Invalid Error o1 doesn't have access to Product & Food.
            System.out.println(p1.product_name); // A
            System.out.println(p1.food_name); // Invalid Error p1 doesn't have access to Food.
            System.out.println(f1.product_name); // A
            System.out.println(f1.food_name); // B
            
        }
    }

    class Product{
        String product_name = "A";
    }
    class Food extends Product{
        String food_name = "B";
    }
    ```

## Rules of Reference Type Casting

1. An object can be referenced by using either of the following
    1. Specific child subclass type
    1. Generic parent superclass type
1. Type Castng rules
    1. Casting is required to assign parent-to-child reference types
    1. No Casting is required to assign child-to-parent reference types

    ```java
    public class SingleScript {
        public static void main(String[] args) {
            Object o1 = new Product();
            Product p1 = new Food();
            Food f1 = new Food();
            Food f2 = new Product(); // This is invalid
            Food f3 = (Food)p1; // Casting required here
            
            System.out.println(o1.product_name); // Invalid Error o1 doesn't have access to Product & Food.
            System.out.println(p1.product_name); // A
            System.out.println(p1.food_name); // Invalid Error p1 doesn't have access to Food.
            
            System.out.println(f1.product_name); // A
            System.out.println(f1.food_name); // B
            
            System.out.println(f3.product_name); // A
            System.out.println(f3.food_name); // B
            
            
        }
    }

    class Product{
        String product_name = "A";
    }
    class Food extends Product{
        String food_name = "B";
    }
    ```

### instanceof -> Verifying object type before Casting

1. If object if `null`. The instance of operator returns a false value.

    ```java
    public class SingleScript {
        public static void main(String[] args) {
            Product p = new Food();
            if (p instanceof Food) {
                System.out.println(((Food) p).food_name); // B
            }
        }
    }

    class Product {
        String product_name = "A";
    }

    class Food extends Product {
        String food_name = "B";
    }
    ```
1. instanceof with apattern matching. More clean

    ```java
    if (p instanceof Food f) {
        System.out.println(f.food_name); // B
    }
    ```

### Reference Code within current or parent object

1. `this` 
2. `super`
3. `this` & `super` keyword not required when the reference is not ambigious
4. Access modifiers would prevent a subclass from accessing parent class variables and methods.

### Subclass constructors

1. The subclass constructor `must` invoke the superclass constructor
1. Subclass can implicitly invoke the superclass constructor only when superclass contains the no-arg constructor.
1. Object class proviedes a no-arg constructor.

# Class and Object Initialization Summary: Good Exercise

1. Explain the order

```java
public class SingleScript {
    static{}
    public static void main(String[] args) {
        Product p1 = new Food();
    }
}
class Product{
    static{}
    {}
    public Product(){}
}
class Food extends Product{
    static{}
    {}
    public Food(){}
}
```

1. All the static initializer are executed only once in order : `SingleScript->Product->Food`
2. Then Product class Instance Initializer & Constructor are fired.
3. Then Food class Instance Initializer & Constructor are fired.