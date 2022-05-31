public class ClientMain {
	public static void main(String[] args) {
		hello("John", null, t -> System.out.println("No Last Name for : " + t));
		helloRunnable("John", null, () -> System.out.println("No Last Name"));
	}

	static void hello(String fName, String lName, Consumer<String> callback) {
		System.out.println(fName);
		if (lName != null) {
			System.out.println(lName);
		} else {
			callback.accept(fName);
		}
	}

	static void helloRunnable(String fName, String lName, Runnable callback) {
		System.out.println(fName);
		if (lName != null) {
			System.out.println(lName);
		} else {
			callback.run();
		}
	}
}
