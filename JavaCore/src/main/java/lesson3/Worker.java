package lesson3;

/**
 * сотрудник с почасовой ЗП
 */
public class Worker extends Employee {
	private final int rateByHour;
	
	public Worker(String name, int rateByHour) {
		super(name);
		this.rateByHour = rateByHour;
	}
	
	@Override
	int calculateSalary() {
		return (int) (20.8 * rateByHour * 8);
	}

}
