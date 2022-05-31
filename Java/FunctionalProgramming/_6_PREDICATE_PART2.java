	
	
	
	
	
	
	public static void main(String[] args) {
		predicate();
	}

	public static void predicate() {
		System.out.println(isPhoneNumberValid("07123456789")); // True
		System.out.println(isPhoneNumberValid("09123456789")); // false
		System.out.println(isPhoneNumberValid("0712345678")); // false

		System.out.println(isPhoneNumberValidPredicate.test("07123456789")); // True
		System.out.println(isPhoneNumberValidPredicate.test("09123456789")); // false
		System.out.println(isPhoneNumberValidPredicate.test("0712345678")); // false
	}

	static Predicate<String> isPhoneNumberValidPredicate = ph -> ph.startsWith("07") && ph.length() == 11;

	static boolean isPhoneNumberValid(String phoneNumber) {
		return phoneNumber.startsWith("07") && phoneNumber.length() == 11;
	}