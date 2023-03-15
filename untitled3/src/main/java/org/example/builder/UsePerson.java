package org.example.builder;

public class UsePerson {
	private Person person;
	
	public void initPerson() {
		person = Person.builder()
								.firstName("Alex")
								.lastName("Smirnov")
								.middleName("Olegovch")
								.age(99)
								.gender("M")
								.build();
		// в билдере не обязательно инициализировать все поля. Остальные NULL
		System.out.println(person);
	}
}
