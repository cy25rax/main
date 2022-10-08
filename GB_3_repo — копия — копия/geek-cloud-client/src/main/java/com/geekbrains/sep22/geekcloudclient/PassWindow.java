package com.geekbrains.sep22.geekcloudclient;

import com.geekbrains.model.CloudMessage;
import com.geekbrains.model.LoginAndPass;
import com.geekbrains.model.Pass;
import io.netty.handler.codec.serialization.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class PassWindow {

    @FXML
    public Button close;
    @FXML
    private TextField password;
    @FXML
    private TextField login;

    private ObjectDecoderInputStream inputStream;
    private ObjectEncoderOutputStream outputStream;

    public void setInputStream(ObjectDecoderInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void setOutputStream(ObjectEncoderOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void sendToServerLogin(ActionEvent actionEvent) throws IOException {
        outputStream.writeObject(new LoginAndPass(login.getText(),password.getText()));
//        Stage stage = (Stage) close.getScene().getWindow();
//        stage.close();

    }

//    public void readMessages() {
//        Runnable task = () -> {
//            try {
//                while (true) {
//                    CloudMessage message = (CloudMessage) inputStream.readObject();
//                    if (message instanceof Pass pass ) {
//                        System.out.println(pass.getLogin());
//                        System.out.println(pass.getPassword());
//                        System.out.println("pass " + pass.isIn());
//                        Stage stage = (Stage) close.getScene().getWindow();
//                        stage.close();
////                        alert = new Alert(Alert.AlertType.INFORMATION);
////                        alert.setTitle("Information Dialog");
////                        alert.setHeaderText("Look, an Information Dialog");
////                        alert.setContentText("login & password is correct");
////                        alert.showAndWait();
//                    } else if (message instanceof Pass pass){
//                        System.out.println("pass " + pass.isIn());
////                        alert = new Alert(Alert.AlertType.INFORMATION);
////                        alert.setTitle("Information Dialog");
////                        alert.setHeaderText("Look, an Information Dialog");
////                        alert.setContentText("login & password is INcorrect");
////                        alert.showAndWait();
//                    }
//                }
//            } catch (IOException | ClassNotFoundException e) {
//                e.printStackTrace();
//            }        };
//        Thread thread = new Thread(task);
//        thread.start();
//    }
}
