package scriptKiddie.freeBank;

public class AbstractFactoryPatternExample {
	public static void main(String[] args) {
		AbstractFactory bankFactory = FactoryCreator.getAbstractFactory("BANK");
		Bank bank = bankFactory.getBank("SBI");
		
		System.out.println("Interest Rate for the bank:"+bank.getBankName()+"5%");
		double rate = 5;
		double amount = 566546;
		int years = 2;
		
		
		AbstractFactory loanFactory = FactoryCreator.getAbstractFactory("Loan");
		Loan loan = loanFactory.getLoan("HOMELOAN");
		loan.getInterestRate(rate);
		loan.calculateLoanPayment(amount, years);
		
	}
}
