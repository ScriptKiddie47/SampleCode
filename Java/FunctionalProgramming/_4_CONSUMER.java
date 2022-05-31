/*
https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/function/Consumer.html

Interface Consumer<T>
Type Parameters:
T - the type of the input to the operation


Represents an operation that accepts a single input argument and returns no result. Unlike most other functional interfaces, Consumer is expected to operate via side-effects.


*/

	public static void main(String[] args) {
		consumer();
	}
	
	public static void consumer() {
		greetCustomer(new Customer("Maria", "9999")); // Hello Maria , thanks for registering 9999
		greetCustomerConsumer.accept(new Customer("Maria", "9999")); // Hello Maria , thanks for registering 9999
	}

	static Consumer<Customer> greetCustomerConsumer = c -> System.out.println(
			"Hello " + c.customerName + " , thanks for registering " + c.customerPhoneNumber);
	
	public static void greetCustomer(Customer customer) {
		System.out.println(
				"Hello " + customer.customerName + " , thanks for registering " + customer.customerPhoneNumber);
	}

	static class Customer {
		private final String customerName;
		private final String customerPhoneNumber;

		public Customer(String customerName, String customerPhoneNumber) {
			this.customerName = customerName;
			this.customerPhoneNumber = customerPhoneNumber;
		}
	}