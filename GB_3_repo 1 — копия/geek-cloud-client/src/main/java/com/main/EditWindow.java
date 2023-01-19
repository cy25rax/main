package com.main;

import com.main.models.parents.Animal;
import com.main.models.parents.HouseAnimal;
import com.main.models.parents.PackAnimal;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class EditWindow {
	public TextField commands;
	public TextField name;
	public Button putAnimal;
	
	private MainWindow sceneController;
	
	public void setSceneController(MainWindow sceneController) {
		this.sceneController = sceneController;
	}
	
	private List<Animal> animalList = new ArrayList<>();
	
	public void setAnimalList(List<Animal> animalList) {
		this.animalList = animalList;
	}
	
	public void putAnimal(ActionEvent actionEvent) {
		Stage stage = (Stage) putAnimal.getScene().getWindow();
		
		String[] commandsArray = commands.getText().split(", ");
		
		for (String str : commandsArray) {
			if (str.matches("[^А-Яа-яЁё]"))
				try {
					throw new Exception("не верный ввод команд");
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
		}
		
		for (Animal animal : animalList) {
			if (name.getText().equals(animal.getName())) {
				if (animal instanceof HouseAnimal) {
					List<String> commandsList = ((PackAnimal) animal).getCommands();
					
					List<String> strings = List.of(commandsArray);
					
					((HouseAnimal) animal).setCommands(
							Stream.concat(commandsList.stream(),
									strings.stream()).toList());
					break;
				}
				
				if (animal instanceof PackAnimal) {
					List<String> commandsList = ((PackAnimal) animal).getCommands();

					List<String> strings = List.of(commandsArray);
					
					((PackAnimal) animal).setCommands(
							Stream.concat(commandsList.stream(),
									strings.stream()).toList());
					break;
				}
			}
		}
		
		sceneController.initialize();
		stage.close();
		
	}
}
