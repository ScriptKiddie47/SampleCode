# Interface

1. Are similar to abstract classes expect that:
    1. Methods are implicitly abstract ( expect default,static and private methods)
    1. A class may implement more than one interace
1. Can only contain constant fields
1. Can be used as a reference type
1. Are an essential component of many design patterns
1. Instance methods are by default public and abstract

    ```java
    interface Perishable{
        public static final int age = 2;
        void perish();
        public default boolean verifyPeriod(){
            return true;
        }
        public static int getMaxPeriod(){
            return age;
        }
    }
    ```

    ```java
    interface A{
        void hello();
    }
    interface B{
        void hello();
    }
    class C implements A,B{
        @Override
        public void hello() {
        }
    }
    ```

1. A concrete class must provide a concrete method implementation for each abstract method signature.
1. A default method can only be defined in an interface.
1. A class must override default interface method `only if it conflicts with another default method`

    ```java
    interface A{
        void hello();
        default void hello2(){}
        default void hello3(){}; // No need to override.
    }
    interface B{
        void hello();
        default void hello2(){}
    }
    class C implements A,B{
        @Override
        public void hello() {
        }
        @Override
        public void hello2() {
        }
    }
    ```

1. Concrete code can be present in the interface only within the default,private & static methods
1. `private` & `static` methods do not cause conflicts
1. Interface can extend another interface.

## Default Method Inheritance

1. A `superclass method` takes priority over an interface default method.
1. An `abstract superclass method` must be overrriden in a subclass.
1. This is horrible and super confusing.

## Interface as a Type

1. Interface can be used in the instance of equation. 

    ```java
    public class SingleScript {
        public static void main(String[] args) {
            Z z = new Z();
            z.a();
            z.b();
            if( z instanceof Y y){
                y.a();;
                y.b(); // Doesn't work.
            }
        }
    }
    ```

### Funcitonal Interface 

1. Interface that define only 1 abstract operation.

    ```java
    @FunctionalInterface
    interface A{
        void B();
    }
    ```

# Generics

1. SE5
1. Compile-time type safety.

    ```java
    List<Object> l1 = new ArrayList<>();
    List<Integer> l2 = new ArrayList<>()
    l1.add("Hello");
    l1.add(100);
    l2.add(120);
    l2.add("Hello"); // Error
    ```

## Java Comparable Interface

1. Descibes a way of comparing current object(this) to another object.
1. Defines a single abstract method `int compareTo(t o)`
1. `public interface Comparable<T>`

    ```java
    public class SingleScript {
        public static void main(String[] args) {
            Product p1 = new Product("A");
            Product p2 = new Product("B");
            Product p3 = new Product("C");
            System.out.println(p2.compareTo(p1)); // 1
            Product[] products = {p1,p2,p3};
            Arrays.sort(products);
        }
    }

    class Product implements Comparable<Product>{
        String name;   
        public Product(String name) {
            this.name = name;
        }
        @Override
        public int compareTo(Product o) {
            // Lexicographically compare strings
            return this.name.compareTo(o.name); 
        }
    }
    ```
## Java Comparator 

1. Describes a way of comparing a pair of objects
1. Defines a single abstract method `int compare(T o1,T o2)`


    ```java
    public class SingleScript {
        public static void main(String[] args) {
            Product p1 = new Product("A");
            Product p2 = new Product("B");
            Product p3 = new Product("C");
            ProductNameSorter productNameSorter = new ProductNameSorter();
            System.out.println(productNameSorter.compare(p2, p1)); // 1
            Product[] products = {p1,p2,p3};
            Arrays.sort(products, productNameSorter);
        }
    }

    class Product{
        String name;   
        public Product(String name) {
            this.name = name;
        }
    }

    class ProductNameSorter implements Comparator<Product>{
        @Override
        public int compare(Product o1, Product o2) {
            return o1.name.compareTo(o2.name);
        }
    }
    ```

## Java Lang Cloneable

1. Example of an interface used as a 'type-marker' or 'tag-interface'
1. Does not have to define any methods
1. Cloning an object means creating a replica of the objects memory.
1. `java.lang.clonable` interface indicates a permission that a object can be cloned.

    ```java
    public class SingleScript {
        public static void main(String[] args) throws Exception {
            Product p1 = new Product("A");
            Product p2 = (Product)p1.clone();
        }
    }

    class Product implements Cloneable{
        String name;   
        public Product(String name) {
            this.name = name;
        }
        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }    
    ```
