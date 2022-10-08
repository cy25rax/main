package com.geekbrains.sep22.geekcloudclient;

import com.geekbrains.model.*;
import io.netty.handler.codec.serialization.ObjectDecoderInputStream;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class CloudMainController extends Application implements Initializable {
    public ListView<String> clientView;
    public ListView<String> serverView;
    public TextField textField;
    private String currentDirectory;
    private Stage stage;

    private ObjectDecoderInputStream objectDecoderInputStream;
    private ObjectEncoderOutputStream objectEncoderOutputStream;
    
    private boolean needReadMessages = true;

    private Alert alert;

    private static List<Path> paths = new ArrayList<>();

    public void downloadFile(ActionEvent actionEvent) throws IOException {
        String fileName = serverView.getSelectionModel().getSelectedItem();
        objectEncoderOutputStream.writeObject(new FileRequest(fileName));
    }

    public void sendToServer(ActionEvent actionEvent) throws IOException {
        String fileName = clientView.getSelectionModel().getSelectedItem();
        if (!Files.isDirectory(Path.of(currentDirectory).resolve(fileName))){
//            System.out.println(Path.of(currentDirectory).resolve(fileName));
            objectEncoderOutputStream.writeObject(new FileMessage(Path.of(currentDirectory).resolve(fileName)));
        } else {
            System.out.println("folder");
            Path myPath = Path.of(currentDirectory).resolve(fileName);
            paths.add(myPath);
            var paths = walk(myPath);
            paths.forEach(path -> {
                System.out.println("folder " + path);
                try {
                    if (Files.isDirectory(path))
                        objectEncoderOutputStream.writeObject(new DirectoryMessage(paths.get(0).relativize(path), paths.get(0)));
                    else objectEncoderOutputStream.writeObject(new FileMessage(paths.get(0).relativize(path)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static List<Path> walk(Path path) throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            for (Path entry : stream) {
                if (Files.isDirectory(entry)) {
                    walk(entry);
                }
                paths.add(entry);
            }
        }
        return paths;
    }

    private void readMessages() {
        try {
            while (needReadMessages) {
                CloudMessage message = (CloudMessage) objectDecoderInputStream.readObject();
                System.out.println("mess " + message.getType());
                if (message instanceof FileMessage fileMessage) {
                    Files.write(Path.of(currentDirectory).resolve(fileMessage.getName()), fileMessage.getBytes());
                    Platform.runLater(() -> fillView(clientView, getFiles(currentDirectory)));
                } else if (message instanceof ListMessage listMessage) {
                    Platform.runLater(() -> fillView(serverView, listMessage.getFiles()));
                } else if (message instanceof Pass pass) {
                    if (pass.isPass()) {
                        System.out.println("correct pass");
//                        alert = new Alert(Alert.AlertType.INFORMATION);
//                        alert.setTitle("Information Dialog");
//                        alert.setHeaderText("Look, an Information Dialog");
//                        alert.setContentText("login & password is correct");
//                        alert.showAndWait();
                        stage.close();
                    } else {
                        System.out.println("dont pass");
//                        alert = new Alert(Alert.AlertType.INFORMATION);
//                        alert.setTitle("Information Dialog");
//                        alert.setHeaderText("Look, an Information Dialog");
//                        alert.setContentText("login & password is Incorrect");
//                        alert.showAndWait();
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Server off");
            e.printStackTrace();
        }
    }

    private void initNetwork() {
        try {
            Socket socket = new Socket("localhost", 8189);
            objectDecoderInputStream = new ObjectDecoderInputStream(socket.getInputStream());
            objectEncoderOutputStream = new ObjectEncoderOutputStream(socket.getOutputStream());
            Thread thread = new Thread(this::readMessages);
            thread.setDaemon(true);
            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadSceneAndSendMessage() {
        try {
            //Load second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("passWindow.fxml"));
            Parent root = loader.load();

            //Get controller of scene2
            PassWindow scene2Controller = loader.getController();
            //Show scene 2 in new window
            stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.resizableProperty().setValue(Boolean.FALSE);
//            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Second Window");
            stage.show();

            scene2Controller.setInputStream(objectDecoderInputStream);
            scene2Controller.setOutputStream(objectEncoderOutputStream);
//            scene2Controller.readMessages();

        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        needReadMessages = true;
        initNetwork();

        loadSceneAndSendMessage();

        setCurrentDirectory(System.getProperty("user.home")+"\\IdeaProjects\\GB_3_repo — копия — копия\\client_files");
        fillView(clientView, getFiles(currentDirectory));
        clientView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String selected = clientView.getSelectionModel().getSelectedItem();
                File selectedFile = new File(currentDirectory + "/" + selected);
                if (selectedFile.isDirectory()) {
                    setCurrentDirectory(currentDirectory + "/" + selected);
                }
            }
        });
        serverView.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2) {
                String selected = serverView.getSelectionModel().getSelectedItem();
                try {
                    objectEncoderOutputStream.writeObject(
                            new UpdateMessage(selected));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setCurrentDirectory(String directory) {
        currentDirectory = directory;
        fillView(clientView, getFiles(currentDirectory));
    }

    private void fillView(ListView<String> view, List<String> data) {
        view.getItems().clear();
        view.getItems().addAll(data);
    }

    private List<String> getFiles(String directory) {
        File dir = new File(directory);
        if (dir.isDirectory()) {
            String[] list = dir.list();
            if (list != null) {
                List<String> files = new ArrayList<>(Arrays.asList(list));
                files.add(0, "..");
                return files;
            }
        }
        return List.of();
    }

    public void renameFile(ActionEvent actionEvent) throws IOException {
        if (clientView.getSelectionModel().getSelectedItem() != null) {
            File file1 = new File(currentDirectory+"\\"+clientView.getSelectionModel().getSelectedItem());
            File file2 = new File(currentDirectory+"\\"+ textField.getText());
            file1.renameTo(file2);
            fillView(clientView, getFiles(currentDirectory));
        }
        if (serverView.getSelectionModel().getSelectedItem() != null) {
            String oldFileName = serverView.getSelectionModel().getSelectedItem();
            String newFileName = textField.getText();
            objectEncoderOutputStream.writeObject(new RenameFile(oldFileName,newFileName));
        }
    }

    public void deleteFile(ActionEvent actionEvent) throws IOException {
        if (clientView.getSelectionModel().getSelectedItem() != null) {
            String fileName = clientView.getSelectionModel().getSelectedItem();
            Files.delete(Path.of(currentDirectory + "\\" +fileName));
            fillView(clientView, getFiles(currentDirectory));
        }
        if (serverView.getSelectionModel().getSelectedItem() != null) {
            String selected = serverView.getSelectionModel().getSelectedItem();
            System.out.println(selected);
            objectEncoderOutputStream.writeObject(new DeleteFile(selected));
        }
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}