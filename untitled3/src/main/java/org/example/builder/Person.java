package org.example.builder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {
	private String firstName;
	private String lastName;
	private String middleName;
	private String country;
	private String address;
	private String phone;
	private int age;
	private String gender;
	
}
