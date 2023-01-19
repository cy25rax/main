package com.main.models.parents;

import java.util.Date;
import java.util.List;

public class PackAnimal extends Animal {
	private List<String> commands;
	
	public List<String> getCommands() {
		return commands;
	}
	
	public void setCommands(List<String> commands) {
		this.commands = commands;
	}
	
	public PackAnimal(String name, Date dateOfBirth) {
		super(name, dateOfBirth);
	}
	
}
