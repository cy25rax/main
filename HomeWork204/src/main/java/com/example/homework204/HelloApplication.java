package com.example.homework204;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        stage.setTitle("Chat");
        stage.setScene(scene);
        stage.show();

    }

    public static PeopleInChat peopleInChat = new PeopleInChat();

    public static void main(String[] args) {
        // я не понял как передать людей в окно от сюда
        // если их создать из контроллера то все ок
        peopleInChat.add("Маша");
        peopleInChat.add("Катя");
        launch();

    }
}