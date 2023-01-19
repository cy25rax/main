package com.main.models.parents;

import java.util.Date;
import java.util.List;

public class HouseAnimal extends Animal {
	private List<String> commands;
	
	public List<String> getCommands() {
		return commands;
	}
	
	public void setCommands(List<String> commands) {
		this.commands = commands;
	}
	
	public HouseAnimal(String name, Date dateOfBirth) {
		super(name, dateOfBirth);
	}
}
