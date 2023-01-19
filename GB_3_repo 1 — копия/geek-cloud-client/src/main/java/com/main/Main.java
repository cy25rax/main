package com.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Animal registry");
        stage.setScene(scene);
    
        MainWindow sceneController = fxmlLoader.getController();
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setTitle("Add new Animal Window");
        sceneController.initialize();
        sceneController.setSceneController(sceneController);
        stage.setAlwaysOnTop(false);
        stage.show();
    }

}