package com.geekbrains.sep22.geekcloudclient;

import com.geekbrains.model.*;
import io.netty.handler.codec.serialization.ObjectDecoderInputStream;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CloudMainController implements Initializable {
    public ListView<String> clientView;
    public ListView<String> serverView;
    public TextField textField;
    private String currentDirectory;
    private PassWindow scene2Controller;
    private boolean isCorrectPass = false;
    private final Lock lock = new ReentrantLock();

    private ObjectDecoderInputStream objectDecoderInputStream;
    private ObjectEncoderOutputStream objectEncoderOutputStream;
    
    private boolean needReadMessages = true;

    private static List<Path> paths;

    public void downloadFile(ActionEvent actionEvent) throws IOException {
        String fileName = serverView.getSelectionModel().getSelectedItem();
        objectEncoderOutputStream.writeObject(new FileRequest(fileName));
    }

    public void sendToServer(ActionEvent actionEvent) throws IOException, InterruptedException {
        String fileName = clientView.getSelectionModel().getSelectedItem();
        if (!Files.isDirectory(Path.of(currentDirectory).resolve(fileName))){
            sendFile(fileName, "");

//            если отправлям папку то отправляем все файлы в папке
        } else {
            paths = new ArrayList<>();
            Path myPath = Path.of(currentDirectory);
            paths.add(Path.of(currentDirectory).resolve(fileName));
            var paths = walk(Path.of(myPath + "\\" + fileName));
            paths.forEach(path -> {
                try {
                    if (Files.isDirectory(path)) {
                        objectEncoderOutputStream.writeObject(
                                new DirectoryMessage(myPath.relativize(path.toAbsolutePath())
                                                            , myPath));
                    } else {
                        sendFile(path.toString(), myPath.relativize(path).toString());
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private void sendFile(String fileName,String fileNameParent) throws IOException, InterruptedException {
        lock.lock();
        byte[] byteArray = new byte[1024*32];
        File file = Path.of(currentDirectory).resolve(fileName).toFile();
        long s = file.length();
        FileInputStream fis = new FileInputStream(file.getPath());
        if (s == 0) {
            objectEncoderOutputStream.writeObject(
                    new FileMessage(Path.of(currentDirectory).resolve(fileName),
                            Path.of(fileNameParent),
                            fis.readAllBytes())
            );
        }
        while (s > 0) {
            int i = fis.read(byteArray);
            objectEncoderOutputStream.writeObject(
                    new FileMessage(Path.of(currentDirectory).resolve(fileName),
                            Path.of(fileNameParent),
                            byteArray)
            );
            s -= i;
        }
        fis.close();
        Thread.sleep(100);
        lock.unlock();
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
                switch (message.getType()){
                    case FILE -> {
                        if (isCorrectPass) {
                            FileMessage fileMessage = (FileMessage) message;
                            if (fileMessage.getPath().equals("")){
                                Files.write(Path.of(currentDirectory).resolve(fileMessage.getName()),
                                        fileMessage.getBytes(),
                                        StandardOpenOption.CREATE,
                                        StandardOpenOption.APPEND);
                            } else {
                                if (Files.exists(Path.of(currentDirectory).resolve(fileMessage.getPath()).getParent())){
                                    Files.write(Path.of(currentDirectory).resolve(fileMessage.getPath()),
                                            fileMessage.getBytes(),
                                            StandardOpenOption.CREATE,
                                            StandardOpenOption.APPEND);
                                } else {
                                    Files.createDirectories(Path.of(currentDirectory).resolve(fileMessage.getPath()).getParent());
                                    Files.write(Path.of(currentDirectory).resolve(fileMessage.getPath()),
                                            fileMessage.getBytes(),
                                            StandardOpenOption.CREATE,
                                            StandardOpenOption.APPEND);
                                }
                            }
                            Platform.runLater(() -> fillView(clientView, getFiles(currentDirectory)));
                        }
                    }
                    case LIST -> {
                        if (isCorrectPass) {
                            ListMessage listMessage = (ListMessage) message;
                            Platform.runLater(() -> fillView(serverView, listMessage.getFiles()));
                        }
                    }
                    case DIRECTORY -> {
                        if (isCorrectPass) {
                            DirectoryMessage directoryMessage = (DirectoryMessage) message;
                            Files.createDirectories(Path.of(currentDirectory).resolve(directoryMessage.getFileName()));
                            Platform.runLater(() -> fillView(clientView, getFiles(currentDirectory)));
                        }
                    }
                    case PASS -> {
                        Pass pass = (Pass) message;
                        if (pass.isPass()) {
                            scene2Controller.setCorrect(true);
                            isCorrectPass = true;
                        }
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
            scene2Controller = loader.getController();
            //Show scene 2 in new window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.resizableProperty().setValue(Boolean.FALSE);
//            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Second Window");
            stage.setAlwaysOnTop(true);
            stage.show();

            scene2Controller.setOutputStream(objectEncoderOutputStream);
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
                File selectedFile = new File(currentDirectory + "\\" + selected);
                if (selectedFile.isDirectory()) {
                    Path path = Path.of(currentDirectory + "\\" + selected).normalize();
                    setCurrentDirectory(path.toString());
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
}