/*

Interface Supplier<T>
Type Parameters:
T - the type of results supplied by this supplier

Represents a supplier of results.

*/



public class RandomWorld {

	public static void main(String[] args) {
		System.out.println(getDBConnectionURL()); // jdbc://localhost:5432/users
		System.out.println(getDBConnectionURLSupplier.get()); // jdbc://localhost:5432/users
	}
	
	static Supplier<String> getDBConnectionURLSupplier = () -> "jdbc://localhost:5432/users";
	
	//Can also be a list
	static Supplier<List<String>> getDBConnectionURLSupplierLists = () -> List.of("jdbc://localhost:5432/users");
	
	
	static String getDBConnectionURL() {
		return "jdbc://localhost:5432/users";
	}
}
