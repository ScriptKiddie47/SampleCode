
/* A SIMPLE JAVA POJO CLASS */

public class Customer {
	
	private final String name;
	private final String email;
	private final String phoneNumber;
	private final LocalDate dob;
	
	public Customer(String name, String email, String phoneNumber, LocalDate dob) {
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.dob = dob;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public LocalDate getDob() {
		return dob;
	}
}

/* A JAVA CLASS WHICH ONLY HAS A JOB TO VALDATE CERTAIN ATTRIBUTES */

public class CustomerValidatorService {

	private boolean isEMailValid(String email) {
		return email.contains("@");
	}

	private boolean isPhoneNumberValid(String phoneNumber) {
		return phoneNumber.contains("+0");
	}

	private boolean isAdult(LocalDate dob) {
		return Period.between(dob, LocalDate.now()).getYears() > 18;
	}

	public boolean isValid(Customer customer) {
		return isEMailValid(customer.getEmail()) & isPhoneNumberValid(customer.getPhoneNumber())
				& isAdult(customer.getDob());
	}
}


	public static void main(String[] args) {
		Customer customer = new Customer("alice", "alice@gmail.com", "+0546161", LocalDate.of(2000, 01, 01));
		CustomerValidatorService customerValidatorService = new CustomerValidatorService();
		boolean valid = customerValidatorService.isValid(customer);
		System.out.println(valid);
	}


/* With the above data we can do simple validation , now lets do the same with Combinator Pattern*/
/*
 * Combinator is an design pattern that allows us to chain functions together,
 * so basically a combinator is a function that might take Other function as an
 * argument and returns !! new Functions !!
 */
 
 public interface CustomerRegistrationValidator extends Function<Customer, ValidationResult> {

	static CustomerRegistrationValidator IsEmailValid() {
		return c -> c.getEmail().contains("@") ? ValidationResult.SUCCESS 
				: ValidationResult.EMAIL_NO_VALID;
	}

	static CustomerRegistrationValidator isPhoneNumberValid() {
		return c -> c.getPhoneNumber().contains("+0") ? ValidationResult.SUCCESS
				: ValidationResult.PHONE_NUMBER_NOT_VALID;
	}

	static CustomerRegistrationValidator isAdult() {
		return c -> Period.between(c.getDob(), LocalDate.now()).getYears() > 18 ? ValidationResult.SUCCESS
				: ValidationResult.IS_NOT_AN_ADULT;
	}
	
	 default CustomerRegistrationValidator andMethod ( CustomerRegistrationValidator other ) {
		return c -> {
			ValidationResult result = this.apply(c);
			return result.equals(ValidationResult.SUCCESS) ? other.apply(c) : result;
		}; 
	 }
}

	public static void main(String[] args) {
		// Using combinator Pattern
		
		Customer customer2 = new Customer("alice", "alice@gmail.com", "+0546161", LocalDate.of(2000, 01, 01));
		ValidationResult result = CustomerRegistrationValidator.IsEmailValid()
			.andMethod(CustomerRegistrationValidator.isPhoneNumberValid())
			.andMethod(CustomerRegistrationValidator.isAdult()).apply(customer2);
		
		System.out.println(result);
		
		if ( result!= ValidationResult.SUCCESS) throw new IllegalStateException(result.name());
		
		System.out.println(CustomerRegistrationValidator.isAdult().apply(customer2));
	}
