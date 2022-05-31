/*

Represents an operation that accepts two input arguments and returns no result. This is the two-arity specialization of Consumer. Unlike most other functional interfaces, BiConsumer is expected to operate via side-effects. 

Interface BiConsumer<T,​U>
Type Parameters:
T - the type of the first argument to the operation
U - the type of the second argument to the operatio

*/


public static void main(String[] args) {
		consumer();
	}
	
	public static void consumer() {
		biConsumer.accept(new Customer("Maria", "9999"), true);  // Hello Maria , thanks for registering 9999
		biConsumer.accept(new Customer("Maria", "9999"), false); // Hello Maria , thanks for registering ******
	}

	// spn - show phone number 
	static BiConsumer<Customer, Boolean> biConsumer = (c, spn) -> System.out.println(
			"Hello " + c.customerName + " , thanks for registering " +  ( spn ? c.customerPhoneNumber : "******" ) );
	
	static class Customer {
		private final String customerName;
		private final String customerPhoneNumber;

		public Customer(String customerName, String customerPhoneNumber) {
			this.customerName = customerName;
			this.customerPhoneNumber = customerPhoneNumber;
		}
	}