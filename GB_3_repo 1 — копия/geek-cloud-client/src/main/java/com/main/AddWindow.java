package com.main;

import com.main.models.counter.Counter;
import com.main.models.enums.AnimaslEnam;
import com.main.models.factory.SimpleFactory;
import com.main.models.parents.Animal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddWindow {
    @FXML
    public Button addAnimal;
    public TextField commands;
    public TextField typeName;
    public TextField dateOfBirth;
    public TextField name;
    public boolean isOpen = false;
    
    public void setSceneController(MainWindow sceneController) {
        this.sceneController = sceneController;
    }
    private MainWindow sceneController;
    
    private List<Animal> animalList = new ArrayList<>();
    
    public List<Animal> getAnimalList() {
        return animalList;
    }
    
    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }
    
    public void addAnimal(ActionEvent actionEvent) throws Exception {
        try (Counter counter = new Counter()){
            Stage stage = (Stage) addAnimal.getScene().getWindow();
            String[] commandsArray = commands.getText().split(", ");
            
            for (String str:commandsArray){
                if (str.matches("[^А-Яа-яЁё]")) throw new Exception("не верный ввод команд");
            }
            
            if (name.getText().matches("[^А-Яа-яЁё]")) throw new Exception("не верный формат имени");
    
            DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
            Date date = format.parse(dateOfBirth.getText());
            
            if (date.after(new Date())) throw new Exception("не верный ввод даты");
            
            for (AnimaslEnam enumValue : AnimaslEnam.values()) {
                if (typeName.getText().equalsIgnoreCase(enumValue.getName())) {
                    animalList.add(SimpleFactory.createAnimal(
                            enumValue,
                            name.getText(),
                            date,
                            List.of(commandsArray)));
                }
            }
            
            counter.add();
            
            sceneController.initialize();
            stage.close();
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
