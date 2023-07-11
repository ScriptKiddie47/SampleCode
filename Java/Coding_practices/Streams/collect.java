package com.scripter;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClientMain {
	public static void main(String[] args) {
		List<Product> productList = new ArrayList<>();

		productList.add(new Product("Tea", 100));
		productList.add(new Product("Cake", 200));
		productList.add(new Product("Cookie", 150));
		productList.add(new Product("Coffee", 300));

		productList.forEach(System.out::println);

		List<Product> collect = productList.stream()
				.collect(Collectors.toList());
		System.out.println("====================");
		collect.forEach(System.out::println);

		IntSummaryStatistics stats = productList.stream()
				.collect(Collectors.summarizingInt(Product::getPrice));

		System.out.println("====================");
		System.out.println(stats); // IntSummaryStatistics{count=4, sum=750, min=100, average=187.500000, max=300}
		System.out.println("====================");
		Function<Product, String> function = new Function<Product, String>() {
			@Override
			public String apply(Product t) {
				return t.getName();
			}
		}; 
		
		String collect2 = productList.stream()
			.collect(Collectors.mapping(function, Collectors.joining(",")));

		
		System.out.println(collect2); // Tea,Cake,Cookie,Coffee
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
