package com.main.models.parents;

import java.util.Date;

public class Animal {
	
	private final String name;
	private final Date dateOfBirth;

	public Animal(String name, Date dateOfBirth) {
		this.name = name;
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getName() {
		return name;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
}
