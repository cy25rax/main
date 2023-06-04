package lesson3;

public class Main {
	
	public static void main(String[] args) {
		EmployeesRepository repository = EmployeesRepository.getINSTANCE();

		repository.addEmployee(new Manager("22", 60_000));
		repository.addEmployee(new Worker("4444", 250));
		repository.addEmployee(new Worker("333", 300));
		repository.addEmployee(new Manager("1", 50_000));
		repository.addEmployee(new Worker("333", 3010));

		repository.getEmployees();

	}
	
}
