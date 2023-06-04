package lesson3;

/**
 * сотрудник с фиксированной ЗП
 */

public class Manager extends Employee {
	private final int rateByMonth;
	
	public Manager(String name, int rateByMonth) {
		super(name);
		this.rateByMonth = rateByMonth;
	}
	
	@Override
	int calculateSalary() {
		return rateByMonth;
	}

}
