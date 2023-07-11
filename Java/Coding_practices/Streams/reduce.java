package com.scripter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class ClientMain {
	public static void main(String[] args) {
		List<Product> productList = new ArrayList<>();

		productList.add(new Product("Tea", 100));
		productList.add(new Product("Cake", 200));
		productList.add(new Product("Cookie", 150));
		productList.add(new Product("Coffee", 300));

		String identity = "DEFAULT";
		BinaryOperator<String> accumulator = new BinaryOperator<String>() {
			@Override
			public String apply(String t, String u) {
				System.out.println("Inside the accumulator : t -> " + t + " , u -> " + u);
				return t + " " + u;
			}
		};

		Optional<String> x1 = productList.stream()
				.map(t -> t.getName())
				.peek(System.out::println)
				.reduce(accumulator); 

		System.out.println("==================================");
		System.out.println(x1.get()); 
		System.out.println("==================================");
		
		String x2 = productList.stream()
				.map(t -> t.getName())
				.reduce(identity, accumulator); 
		
		System.out.println("==================================");
		System.out.println(x2); 
		System.out.println("==================================");
		
		BiFunction<String, Product, String> biFunction = new BiFunction<String, Product, String>() {
			
			@Override
			public String apply(String t, Product u) {
				System.out.println("Inside BiFunction : t -> " + t + " , u.getName() -> " + u.getName());
				return u.getName() + " " + t;
			}
		};
		
		String x3 = productList.stream()
			.parallel()
			.reduce(identity, biFunction, accumulator);
		System.out.println("==================================");
		System.out.println(x3);
		System.out.println("==================================");
		
	}
}

class Order {
	private List<Product> items;

	public Order(List<Product> items) {
		super();
		this.items = items;
	}

	public Stream<Product> items() {
		return items.stream();
	}

	public List<Product> getItems() {
		return items;
	}

	public void setItems(List<Product> items) {
		this.items = items;
	}
}

class Product {
	String name;
	int price;

	public Product(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", price=" + price + "]";
	}

}
