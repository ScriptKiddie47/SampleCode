package com.scripter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class ClientMain {
	public static void main(String[] args) {
		List<Product> productList = new ArrayList<>();

		productList.add(new Product("Tea", 100));
		productList.add(new Product("Cake", 200));
		productList.add(new Product("Cookie", 150));
		productList.add(new Product("Coffee", 300));

		BinaryOperator<String> accumulator = new BinaryOperator<String>() {
			@Override
			public String apply(String t, String u) {
				return t + " " + u;
			}
		};

		Optional<String> x1 = productList.stream()
				.map(t -> t.getName())
				.reduce(accumulator); // Performs accumulation of elements

		System.out.println(x1.get()); // Tea Cake Cookie Coffee

		String identity = ""; // wtf is an identity in what context. WTF IS THIS!!!!
		String x2 = productList.stream()
				.map(t -> t.getName())
				.reduce(identity, accumulator); // Performs accumulation of elements

		System.out.println(x2); // Tea Cake Cookie Coffee
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
