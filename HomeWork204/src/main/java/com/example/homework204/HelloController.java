package com.example.homework204;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    public TextArea messageArea;
    @FXML
    public TextArea messagePeople;
    @FXML
    private TextField messageField;


    public void checkButton(ActionEvent actionEvent) {

        messageArea.appendText("Пользователь: \n");
        messageArea.appendText("\t" + messageField.getText() + "\n");

        messageArea.appendText("Собеседник \n");
        messageArea.appendText("\tскажи еще что то\n");


        messageField.clear();
        messageField.requestFocus();
    }

    public void menuNick(ActionEvent actionEvent) {

    }
}