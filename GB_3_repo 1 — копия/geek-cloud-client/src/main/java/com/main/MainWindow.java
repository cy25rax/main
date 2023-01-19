package com.main;

import com.main.models.parents.Animal;
import com.main.models.parents.HouseAnimal;
import com.main.models.parents.PackAnimal;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class MainWindow {
	public ListView<String> dataView;
	public ListView<String> animalsView;
	public ListView<String> commandsView;
	private MainWindow sceneController;
	
	public void setSceneController(MainWindow sceneController) {
		this.sceneController = sceneController;
	}

	private List<Animal> animalList = new ArrayList<>();
	
	public void initialize() {
		
		fillView(animalsView, animalList);
		animalsView.setOnMouseClicked(event -> {
			if (event.getClickCount() == 1) {
				String selected = animalsView.getSelectionModel().getSelectedItem();
				
				dataView.getItems().clear();
				commandsView.getItems().clear();
				
				for (Animal animal:animalList){
					if (selected.equals(animal.getName())) {
						dataView.getItems().add(animal.getDateOfBirth().toString());
						if (animal instanceof HouseAnimal) {
							commandsView.getItems().addAll(((HouseAnimal) animal).getCommands());
						}
						if (animal instanceof PackAnimal) {
							commandsView.getItems().addAll(((PackAnimal) animal).getCommands());
						}
					}
				}
			}
		});
	}
	
	private void fillView(ListView<String> view, List<Animal> animalList) {
		view.getItems().clear();
		for (Animal animal : animalList) {
			view.getItems().add(animal.getName());
		}
	}
	
	public void addAnimal(ActionEvent actionEvent) {
		loadAddWindow();
	}
	
	private void loadAddWindow() {
		try {
			//Load second scene
			FXMLLoader loader = new FXMLLoader(getClass().getResource("addWindow.fxml"));
			Parent root = loader.load();
			
			//Get controller of scene2
			AddWindow scene2Controller = loader.getController();
			//Show scene 2 in new window
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.resizableProperty().setValue(Boolean.FALSE);
//            stage.initStyle(StageStyle.UNDECORATED);
			stage.setTitle("Add new Animal Window");
			scene2Controller.setAnimalList(animalList);
			stage.setAlwaysOnTop(true);
			stage.show();
			
			scene2Controller.setSceneController(sceneController);
			
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
	
	public void showAllert(ActionEvent actionEvent) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText("some text");
		alert.showAndWait();
	}
	
	public void editAnimal(ActionEvent actionEvent) {
		loadEditWindow();
	}
	
	private void loadEditWindow() {
		try {
			//Load second scene
			FXMLLoader loader = new FXMLLoader(getClass().getResource("editWindow.fxml"));
			Parent root = loader.load();
			
			//Get controller of scene2
			EditWindow scene3Controller = loader.getController();
			//Show scene 2 in new window
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.resizableProperty().setValue(Boolean.FALSE);
//            stage.initStyle(StageStyle.UNDECORATED);
			stage.setTitle("Edit Animal Window");
			scene3Controller.setAnimalList(animalList);
			scene3Controller.setSceneController(sceneController);
			stage.setAlwaysOnTop(true);
			stage.show();
			
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
	
}