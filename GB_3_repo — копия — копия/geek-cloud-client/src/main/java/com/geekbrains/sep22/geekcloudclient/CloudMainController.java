package com.geekbrains.sep22.geekcloudclient;

import com.geekbrains.model.*;
import io.netty.handler.codec.serialization.ObjectDecoderInputStream;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class CloudMainController implements Initializable {
    public ListView<String> clientView;
    public ListView<String> serverView;
    public TextField textField;
    private String currentDirectory;

    private ObjectDecoderInputStream objectDecoderInputStream;
    private ObjectEncoderOutputStream objectEncoderOutputStream;
    
    private boolean needReadMessages = true;

    public void downloadFile(ActionEvent actionEvent) throws IOException {
        String fileName = serverView.getSelectionModel().getSelectedItem();
        objectEncoderOutputStream.writeObject(new FileRequest(fileName));
    }

    public void sendToServer(ActionEvent actionEvent) throws IOException {
        String fileName = clientView.getSelectionModel().getSelectedItem();
        objectEncoderOutputStream.writeObject(new FileMessage(Path.of(currentDirectory).resolve(fileName)));
    }

    private void readMessages() {
        try {
            while (needReadMessages) {
                CloudMessage message = (CloudMessage) objectDecoderInputStream.readObject();
                if (message instanceof FileMessage fileMessage) {
                    Files.write(Path.of(currentDirectory).resolve(fileMessage.getFileName()), fileMessage.getBytes());
                    Platform.runLater(() -> fillView(clientView, getFiles(currentDirectory)));
                } else if (message instanceof ListMessage listMessage) {
                    Platform.runLater(() -> fillView(serverView, listMessage.getFiles()));
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
//            factory.getThread(this::readMessages, "cloud-client-read-thread")
//                    .start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        needReadMessages = true;
        initNetwork();
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
            String fileName = clientView.getSelectionModel().getSelectedItem();
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
}
