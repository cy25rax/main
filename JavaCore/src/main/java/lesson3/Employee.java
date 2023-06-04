package lesson3;

public abstract class Employee {
	
	private String name;
	
	public String getName() {
		return name;
	}
	abstract int calculateSalary();
	
	public Employee(String name) {
		this.name = name;
	}
	
}
