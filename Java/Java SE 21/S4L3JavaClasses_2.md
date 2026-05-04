# Polymorphism ( Many Forms ) :  Method Overriding

1. Method is declared in a superclass and is overriden in a subclass.

    ```java
    public class SingleScript {
        public static void main(String[] args) {
            Product p1 = new Product();
            System.out.println(p1.name + " : " + p1.getPrice()); // Product : 100
            Product p2 = new Food();
            System.out.println(p2.name + " : " + p2.getPrice()); // Product : 120
            Food f1 = (Food)p2;
            System.out.println(f1.name + " : " + f1.getPrice()); // Food : 120
        }
    }

    class Product{
        String name = "Product";
        public int getPrice(){
            return 100;
        }
    }
    class Food extends Product{
        String name = "Food";
        public int getPrice(){
            return 120;
        }
    }
    ```

# Abstract Class

1. Cannot be directly instantiated. 
1. May contain normal variables and methods, which are inherited by subclass as usual.
1. Classes extending abstract class `MUST` implement asbract methods

    ```java
    abstract class Product {
        abstract void serve();
    }
    class Food extends Product {
        @Override
        void serve() {
        }
    }
    ```

## Equals 

1. Lets write a good Equals method to compare objects
1. The `==` operator compares values in the `stack`. It can be used to compare primitives or to determine if two references are point to the same object.
1. The overriding method `equals` enables us to compare object content in the `heap`.

    ```java
    public class SingleScript {
        public static void main(String[] args) {
            Product p1 = new Product(10);
            Product p2 = new Product(10);
            System.out.println(p1 == p2); // False
            System.out.println(p1.equals(p2)); // True
        }
    }

    class Product {
        int id;

        public Product(int id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Product)) {
                return false;
            }
            Product other = (Product) obj;
            return this.id == other.id;
        }
    }
    ```

## HashCode

1. `hashCode` generates an object identity as an `int` value.
1. Must consistently return the same `int` value for the same instance. Used for bucketing hashed collections such as HashSet,HashMap,Hashtable
1. The `hashCode` method should return the same `int` value for any pair of objects that areconsiderered to be the same when compared with the equals method.
1. The `Objects` class contains the `hash` method that generates a hash code value for a number of objects.

    ```java
    import java.util.Objects;

    public class SingleScript {
        public static void main(String[] args) {
            Product p1 = new Product(10);
            Product p2 = new Product(10);
            System.out.println(p1.hashCode()); // 41
            System.out.println(p2.hashCode()); // 41
        }
    }

    class Product {
        int id;
        public Product(int id) {
            this.id = id;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Product)) {
                return false;
            }
            Product other = (Product) obj;
            return this.id == other.id;
        }
        @Override
        public int hashCode() {
            return Objects.hash(id);
        }   
    }
    ```

## The Golden Contract
1. `hashCode()` and `equals()` must always be overridden together, following this rule:
1. If `a.equals(b)` is true, then `a.hashCode() == b.hashCode()` must also be true.
1. The reverse doesn't have to hold — two objects can share the same hash code without being equal (this is called a `collision`).

# Records

1. Immutable and implicity final data class
1. Implicitly extends `java.lang.Record` class
1. Accesor,constructor,equals,hashCode & toString methods are created automatically.
1. It can implement Interfaces ✅ Cannot extend classes ❌ because class marked as final cannot be extended.

    ```java
    record Product(String name) {}
    ```

1. Custom Record Constructors
    1. Convential - We have see it all day
    1. Compact Constructor
1. Below is honestly confusing.

    ```java
    record Product(String name) {
        // Compact constructor
        // Implies the argument list from the header.
        public Product{
            name = name.toLowerCase();
            // this.name = name; is auto generated
        }
    }
    ```
## Pattern Matching for Switch

1. Yeah its there. Ignoring it.


# Factory Methods

1. Hide the use of a specific constructor

    ```java

    public class SingleScript {
        public static Product createProduct(String key) {
            switch (key) {
                case "FOOD":
                    return new Food();
                case "DRINK":
                    return new Drink();
                default:
                    return null;
            }
        }
        public static void main(String[] args) {
            Product p1 = SingleScript.createProduct("FOOD");
            System.out.println(p1 instanceof Food); // True
        }
    }

    abstract class Product {
    }
    class Food extends Product {
    }
    class Drink extends Product {
    }
    ```