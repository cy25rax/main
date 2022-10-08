package com.geekbrains.sep22.geekcloudclient;

import com.geekbrains.model.LoginAndPass;
import io.netty.handler.codec.serialization.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class PassWindow {

    @FXML
    public Button close;
    @FXML
    private TextField password;
    @FXML
    private TextField login;
    private boolean isCorrect = false;
    private Alert alert;

    private ObjectEncoderOutputStream outputStream;


    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public void setOutputStream(ObjectEncoderOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void sendToServerLogin(ActionEvent actionEvent) throws IOException, InterruptedException {
        outputStream.writeObject(new LoginAndPass(login.getText(),password.getText()));
        Stage stage = (Stage) close.getScene().getWindow();
        stage.setAlwaysOnTop(false);
        Thread.sleep(100);
        if (isCorrect) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("login & password is correct");
            alert.showAndWait();
            stage.close();
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("login & password is Incorrect");
            alert.showAndWait();
            stage.setAlwaysOnTop(true);
        }
    }
}
