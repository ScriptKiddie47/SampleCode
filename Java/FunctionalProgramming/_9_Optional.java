	public static void main(String[] args) {
		myOptionalLogic();
	}

	public static void myOptionalLogic() {
		Object v1 = Optional.ofNullable("Hello").orElseGet(() -> "Default Value");
		System.out.println(v1);
		Object v2 = Optional.ofNullable(null).orElseGet(() -> "Default Value");
		System.out.println(v2);
//		Object v3 = Optional.ofNullable (null).orElseThrow(() -> new IllegalStateException());
//		System.out.println(v3);
		
		Optional.ofNullable ("Some Value").ifPresent(t -> System.out.println(t));
		Optional.ofNullable ("john@gmail.com").ifPresent(email -> System.out.println("Sending email to : " + email));
		Optional.ofNullable (null).ifPresentOrElse(email -> System.out.println("Sending email to : " + email),() -> System.out.println("Cannot send email "));
	}