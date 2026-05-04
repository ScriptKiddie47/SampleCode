# Nested Classes

### Type of Nested Classes

1. `static` nested classes is associated with the static context of the outer class.
1. `Member` inner class is associated with the instance context of the outer class.
1. `Local` inner class is associated with the context of a specific method
1. `Anonymous Inner Class` inline implementation
1. Static & memeber nested classes can be defined as:
    1. `public`,`protected` or `default` can be accessed externally.
    1. `private` can be referenced only inside their outer class.

    ```java
    public class SingleScript {
        public static void main(String[] args) {
            Outer.StaticNested x = new Outer.StaticNested();
        }
    }
    class Outer {
        public static class StaticNested{
        }
    }
    ```
1. Create an instance of private static Nester Class.

    ```java
    public class SingleScript {
        public static void main(String[] args) {
            Outer.createInstance();
        }
    }
    class Outer {
        private static class StaticNested{
        }
        public static void createInstance(){ new StaticNested();}
    }
    ```
## Static Nested Class

1. Static classes can only be an inner class.
1. Static Nested class is associated with the static context of the outer class
1. To create an instance of static nested class,you do not need to create instance of outer class.
1. Can only access static variables and methods of the outer class.

    ```java
    public class SingleScript {
        public static void main(String[] args) {
            Order.createShippingMode("Fast");
            Order.createShippingMode("Normal");
        }
    }
    class Order{
        public static void createShippingMode(String description){
            new ShippingMode(description);
        }
        private static class ShippingMode{
            private String description;
            public ShippingMode(String description){
                this.description = description;
            }
        }
    }
    ```
## Member Inner Class

1. Member inner class is associated with the instance context of the outer class.
1. To create an instance of a member inner class, you must create an instance of outer class first.
1. Can access private variables and methods of outer class
1. Can access both static and instance variables and methods of the outer class.


    ```java
    public class SingleScript {
        public static void main(String[] args) {
            Order order1 = new Order();
            order1.addItem("Artemus3");
            System.out.println(order1.getShippingModes()); // [Artemus3]

        }
    }
    class Order{
        private Set<ShippingMode> shippingModes = new HashSet<>();
        public void addItem(String description){
            shippingModes.add(new ShippingMode(description));
        }
        class ShippingMode{
            private String description;
            public ShippingMode(String description){
                this.description = description;
            }
            @Override
            public String toString() {
                return description;
            }
        }
        public Set<ShippingMode> getShippingModes(){
            return shippingModes;
        }
    }
    ```

## Local Inner Class

1. Local Inner Class is associated with the context of a specific method.
1. `Instances` of the local inner class can only be created within the outer method context.
1. It contains logic complex enough to require the algorithms to be wrapped up in a class.
1. Outer method local variables and parameters can only be accessed if they are `final` or `effectively final`.

```java
public class SingleScript {
    public static void main(String[] args) {
    }
}
class Order{
    private Set<String> shippingModes = new HashSet<>();
    public void manageTax(final String saleLocation){
        class OrderTaxManager{
            private int findRate(){
            }
        }
        OrderTaxManager manager = new OrderTaxManager();
    }
}
```

## Anonymous Inner Class

1. Implementation of an interface or extension of a class.
1. It extends a parent class or implement an interface to override operations
1. It is implemented inline and instantiated immediately.
1. 1. Outer method local variables and parameters can only be accessed if they are `final` or `effectively final`.


```java
public class SingleScript {
    public static void main(String[] args) {
        Order order = new Order(){
            @Override
            public int getDiscount() {
                return 2;
            }     
        };
        System.out.println(order.getDiscount());
    }
}
class Order{
    public int getDiscount(){
        return 1;
    }
}
class OnlineOrder extends Order{
    @Override
    public int getDiscount(){
        return 2;
    }
}
```

1. Most commonly used to provide inline interface implementation.
1. Functional Interfaces define only one abstract method that must be overriden.

    ```java
    public class SingleScript {
        public static void main(String[] args) {
            List<Product> products = new ArrayList<>();
            products.add(new Product("TV"));
            products.add(new Product("Fridge"));
            Collections.sort(products,new Comparator<Product>() {
                @Override
                public int compare(Product o1, Product o2) {
                return o1.name().compareTo(o2.name());
                }
            });
        }
    }
    record Product(String name){}
    ```

## Lambda Expression

1. Lambda Token `->`
1. Generics infers which parameters the method should have
1. Return type infers a return statement
1. Formal body `{}` amd `return` statements are optional when using a simple expression.
1. Expression can be predefined and re-used

```java
public class SingleScript {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product("TV"));
        products.add(new Product("Fridge"));
        Collections.sort(products,(o1, o2) -> o1.name().compareTo(o2.name()));
    }
}
record Product(String name){}
```

1. We can use modifiers on parameters like final that would prevent reassignment within the expression.

## Method References 

1. Lambda Expression may use method referencing
1. `Reference method` is semantically identical to the method that lambda expression is implementing.
1. `<Class>::<staticMethod>` Reference a static method
1. `<object>::<instanceMethod>` Reference an instance method of a particular object
1. `<Class>::<instanceMethod>` Reference an instance method of an arbitary object of a particular type.
1. `<Class>::new` - Reference a constructor

```java
public class SingleScript {
    public static void main(String[] args) {
        TextFilter filter = new TextFilter();
        List<String> sList = new ArrayList<>();
        sList.removeIf(t -> TextFilter.removeA(t));
        // removeIf implements a Predicate which only has 1 parameter
        sList.removeIf(TextFilter::removeA);
        Collections.sort(sList,(o1, o2) -> filter.sortText(o1, o2));
        Collections.sort(sList,filter::sortText);
        Collections.sort(sList,(o1, o2) -> o1.compareToIgnoreCase(o2));
        Collections.sort(sList,String::compareToIgnoreCase);
    }
}
class TextFilter{
    public static boolean removeA(String s){
        return s.equals("Remove A");
    }
    public int sortText(String s1,String s2){
        return s1.compareTo(s2);
    }
}
```

1. `String` has an instance method compareToIgnoreCase


### Default and Static Methods in Functional Interface

1. Lambda expression implements the only `abstract method` provided by the functional interface.
1. `default` and `static` methods may be defined by the interface to provide additional features.
1. `default` methods provided by the `java.util.Comparator` interface.
    1. `thenComparing` adds additional `comparators`.
    1. `reversed` reverses sorting order.
1. Static Methods provided by Comparator Interface
    1. `nullsFirst` and `nullsLast` return `comparators` that enable sorting collections.

```java
public class SingleScript {
    public static void main(String[] args) {
        List<Product> pList = ....
        Comparator<Product> sortName = (o1, o2) -> o1.name().compareTo(o2.name());
        Comparator<Product> sortPrices = (o1, o2) -> o1.price().compareTo(o2.price());
        Collections.sort(pList,sortName.thenComparing(sortPrices).reversed());
        pList.add(null);
        Collections.sort(pList,Comparator.nullsFirst(sortName));
    }
}
record Product(String name,BigDecimal price){}
```