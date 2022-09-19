package com.geekbrains.sep22.geekcloudclient;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.*;

public class CloudMainController implements Initializable {
    public ListView<String> clientView;
    public ListView<String> serverView;
    private String currentDirectory;

    private DataInputStream dis;

    private DataOutputStream dos;

    private Socket socket;

    private static final Integer BATCH_SIZE = 256;
    private byte[] batch;



    private static final String SEND_FILE_COMMAND = "file";
    private static final String SHOW_LIST_FILE_COMMAND = "fileList";
    private static final String RECEIVE_FILE_COMMAND = "receiveFile";


    public void sendToServer(ActionEvent actionEvent) {
        String fileName = clientView.getSelectionModel().getSelectedItem();
        String filePath = currentDirectory + "/" + fileName;
        File file = new File(filePath);
        if (file.isFile()) {
            try {
                dos.writeUTF(SEND_FILE_COMMAND);
                dos.writeUTF(fileName);
                dos.writeLong(file.length());
                try (FileInputStream fis = new FileInputStream(file)) {
                    byte[] bytes = fis.readAllBytes();
                    dos.write(bytes);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (Exception e) {
                System.err.println("e = " + e.getMessage());
            }
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("start");
        fillView(serverView, getServerListFiles());
        System.out.println("end");
    }

    private void initNetwork() {
        try {
            socket = new Socket("localhost", 8189);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (Exception ignored) {}
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initNetwork();
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("thread start");
//                if (dis.readUTF().equals(SHOW_LIST_FILE_COMMAND))
//                  fillView(serverView, getServerListFiles());
//                System.out.println("thread fin");
//            }
//        });
//        thread.start();
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        fillView(serverView, getServerListFiles());


        setCurrentDirectory(System.getProperty("user.home")+"\\IdeaProjects\\GB_3_repo\\client_files");
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
    }

    private List<String> getServerListFiles() {
        List<String> files = new ArrayList<>();
        try {
            dos.writeUTF(SHOW_LIST_FILE_COMMAND);

            System.out.println(SHOW_LIST_FILE_COMMAND);

            int size = dis.readInt();

            System.out.println("size =" +  size);

            for (int i = 0; i < size; i++) {
                files.add(dis.readUTF());
            }

            System.out.println(files);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
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
        // file.txt 125 b
        // dir [DIR]
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

    public void sendToClient(ActionEvent actionEvent) {
        String fileName = serverView.getSelectionModel().getSelectedItem();
        try {
            dos.writeUTF(RECEIVE_FILE_COMMAND);
            dos.writeUTF(fileName);
            if (dis.readBoolean()) {
            try {
                long size = dis.readLong();
                try (FileOutputStream fos = new FileOutputStream(currentDirectory + "/" + fileName)) {
                    for (int i = 0; i < (size + BATCH_SIZE - 1) / BATCH_SIZE; i++) {
                        int read = dis.read(batch);
                        fos.write(batch, 0, read);
                    }
                } catch (Exception ignored) {}
                Thread.sleep(100);
            } catch (Exception e) {
                System.err.println("e = " + e.getMessage());
            }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        fillView(clientView, getFiles(currentDirectory));
    }
}
