package scriptKiddie.freeBank;

public class LoanFactory extends AbstractFactory {

	@Override
	public Bank getBank(String bank) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Loan getLoan(String loan) {
		// TODO Auto-generated method stub
		if (loan == null) {
			return null;
		}
		if (loan.equalsIgnoreCase("HOMELOAN")) {
			return new HomeLoan();
		}
		if (loan.equalsIgnoreCase("BUSINESSLOAN")) {
			return new BussinessLoan();
		}
		if (loan.equalsIgnoreCase("EDUCATIONLOAN")) {
			return new EducationLoan();
		}
		return null;
	}
}
