package com.main.models.enums;

public enum AnimaslEnam {
	CAMEL("верблюд"),
	CAT ("кот"),
	DOG ("собака"),
	DONKEY ("осел"),
	HAMSTER ("хомяк"),
	HORSE ("лошадь");
	
	private final String name;
	
	public String getName() {
		return name;
	}
	
	AnimaslEnam(String name) {
		this.name = name;
	}
}
