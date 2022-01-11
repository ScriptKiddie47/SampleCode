package plannigcommission;

public class GenerateBill {
	public static void main(String[] args) {
		GetPlanFactory planFactory = new GetPlanFactory();
		int units = 5;
		Plan p = planFactory.getPlan("DomesticPLan");
		System.out.println("Bill amount for DomesticPlan of units: "+units+" Rs:");
		p.getRate();
		p.calculate(units);
	}
}
