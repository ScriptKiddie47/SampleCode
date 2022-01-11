package scriptKiddie.freeBank;

public class BankFactory extends AbstractFactory{

	@Override
	public Bank getBank(String bank) {
		// TODO Auto-generated method stub
		if(bank == null) {
			return null;
		}
		if(bank.equalsIgnoreCase("HDFC")) {
			return new HDFC();
		}
		if(bank.equalsIgnoreCase("ICICI")) {
			return new ICICI();
		}
		if(bank.equalsIgnoreCase("SBI")) {
			return new SBI();
		}
		return null;	
	}

	@Override
	public Loan getLoan(String loan) {
		// TODO Auto-generated method stub
		return null;
	}

}
