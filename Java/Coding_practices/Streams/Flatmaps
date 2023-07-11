package com.scripter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ClientMain {
	public static void main(String[] args) {
		List<Product> productList = new ArrayList<>();

		productList.add(new Product("Tea", 100));
		productList.add(new Product("Cake", 200));
		
		List<Product> productList2 = new ArrayList<>();

		productList2.add(new Product("Cookie", 150));
		productList2.add(new Product("Coffee", 300));
		productList2.add(new Product("Cake", 200));
		
		List<Product> productList3 = new ArrayList<>();

		productList3.add(new Product("Tea", 100));
		productList3.add(new Product("Cookie", 150));
		
		
		List<Order> orderList = new ArrayList<>();
		orderList.add(new Order(productList));
		orderList.add(new Order(productList2));
		orderList.add(new Order(productList3));
		
		for (Order o : orderList) {
			for (Product p : o.getItems()) {
				System.out.println(p);
			}
		}
		
		System.out.println("=====================");
		
		orderList.stream()
			.flatMap(t -> t.items())  // generates a stream of products
			.mapToInt(t -> t.getPrice())
			.forEach(System.out::println);
	
		// We went from Order list to Product list to Intergers (list).....
	
	}
}

class Order{
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
