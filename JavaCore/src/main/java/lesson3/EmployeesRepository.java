package lesson3;

import java.util.*;

public class EmployeesRepository {
	
	private static EmployeesRepository INSTANCE;
	private static List<Employee> employees;
	
	
	public static EmployeesRepository getINSTANCE() {
		if (INSTANCE == null) {
			INSTANCE = new EmployeesRepository();
			employees = new ArrayList<>();
		}
		return INSTANCE;
	}
	
	private EmployeesRepository() {
	}
	
	public void addEmployee(Employee employee) {
		employees.add(employee);
	}
	
	public void getEmployees() {
		employees.sort(Comparator.comparing(Employee::calculateSalary));
		for (Employee elt : employees) {
			System.out.printf("worker name: %4s have salary: %,d \n", elt.getName(), elt.calculateSalary());
		}
	}
}
