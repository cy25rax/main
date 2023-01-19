package com.main.models.factory;

import com.main.models.children.*;
import com.main.models.enums.AnimaslEnam;
import com.main.models.parents.Animal;

import java.util.Date;
import java.util.List;

public class SimpleFactory {
	
	public static Animal createAnimal (AnimaslEnam type,
									   String name,
									   Date dateOfBirth,
									   List<String> commands){
		Animal animal = null;
		switch (type) {
			case CAMEL -> {
				Camel camel = new Camel(name, dateOfBirth);
				camel.setCommands(commands);
				System.out.println("create camel");
				return camel;
			}
			case CAT -> {
				Cat cat = new Cat(name, dateOfBirth);
				cat.setCommands(commands);
				System.out.println("create cat");
				return cat;
			}
			case DOG -> {
				Dog dog = new Dog(name, dateOfBirth);
				dog.setCommands(commands);
				System.out.println("create dog");
				return dog;
			}
			case DONKEY -> {
				Donkey donkey = new Donkey(name, dateOfBirth);
				donkey.setCommands(commands);
				System.out.println("create donkey");
				return donkey;
			}
			case HAMSTER -> {
				Hamster hamster = new Hamster(name, dateOfBirth);
				hamster.setCommands(commands);
				System.out.println("create hamster");
				return hamster;
			}
			case HORSE -> {
				Horse horse = new Horse(name, dateOfBirth);
				horse.setCommands(commands);
				System.out.println("create horse");
				return horse;
			}
		}
		return animal;
	}
}
