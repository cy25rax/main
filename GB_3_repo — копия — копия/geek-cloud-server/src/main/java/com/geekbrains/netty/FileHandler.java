package com.geekbrains.netty;

import com.geekbrains.model.*;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;

public class FileHandler extends SimpleChannelInboundHandler<CloudMessage> {

    private Path serverDir;
    private Connection connection;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        connection = DriverManager.getConnection("jdbc:sqlite:javadb.db");
        serverDir = Path.of("server_files");
        ctx.writeAndFlush(new ListMessage(serverDir));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        connection.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CloudMessage cloudMessage) throws Exception {
        System.out.println("mess " + cloudMessage.getType());
        switch (cloudMessage.getType()){
            case FILE -> {
                FileMessage fileMessage = (FileMessage) cloudMessage;
                Files.write(serverDir.resolve(fileMessage.getName()), fileMessage.getBytes());
                ctx.writeAndFlush(new ListMessage(serverDir));
            }
            case LIST -> {

            }
            case FILE_REQUEST -> {
                FileRequest fileRequest = (FileRequest) cloudMessage;
                ctx.writeAndFlush(new FileMessage(serverDir.resolve(fileRequest.getFileName())));
            }
            case DIRECTORY -> {
                DirectoryMessage directoryMessage = (DirectoryMessage) cloudMessage;
                Files.createDirectories(serverDir.resolve(directoryMessage.getFileName()));
            }
            case LOGIN_PASSWORD -> {
                LoginAndPass pass = (LoginAndPass) cloudMessage;
                ctx.writeAndFlush(new Pass(auth(pass.getLogin(), pass.getPassword())));
                System.out.println(" pass "+auth(pass.getLogin(), pass.getPassword()));
            }
            case DELETE_FILE -> {
                DeleteFile deleteFile = (DeleteFile) cloudMessage;
                Files.delete(Path.of(serverDir + "\\" + deleteFile.getFileName()));
                ctx.writeAndFlush(new ListMessage(serverDir));
            }
            case RENAME_FILE -> {
                RenameFile renameFile = (RenameFile) cloudMessage;
                File file1 = new File(serverDir + "\\" + renameFile.getOldFileName());
                File file2 = new File(serverDir + "\\" + renameFile.getNewFileName());
                file1.renameTo(file2);
                ctx.writeAndFlush(new ListMessage(serverDir));
            }
            case UPDATE_MESSAGE -> {
                UpdateMessage updateMessage = (UpdateMessage) cloudMessage;
                if (Files.isDirectory(Path.of(String.valueOf(serverDir.resolve(updateMessage.getName()))))) {
                    serverDir = Path.of(serverDir + "\\" + updateMessage.getFileName());
                    ctx.writeAndFlush(new ListMessage(serverDir));
                }
            }

        }
//        if (cloudMessage instanceof FileMessage fileMessage) {
//            Files.write(serverDir.resolve(fileMessage.getFileName()), fileMessage.getBytes());
//            ctx.writeAndFlush(new ListMessage(serverDir));
//        } else if (cloudMessage instanceof FileRequest fileRequest) {
//            ctx.writeAndFlush(new FileMessage(serverDir.resolve(fileRequest.getFileName())));
//        } else if (cloudMessage instanceof UpdateMessage updateMessage) {
//            if (Files.isDirectory(Path.of(String.valueOf(serverDir.resolve(updateMessage.getName()))))){
//                serverDir = Path.of(serverDir + "\\" + updateMessage.getFileName());
//                ctx.writeAndFlush(new ListMessage(serverDir));
//            }
//        } else if (cloudMessage instanceof DeleteFile deleteFile) {
//            Files.delete(Path.of(serverDir + "\\" + deleteFile.getFileName()));
//            ctx.writeAndFlush(new ListMessage(serverDir));
//        } else if (cloudMessage instanceof RenameFile renameFile) {
//            File file1 = new File(serverDir + "\\" + renameFile.getOldFileName());
//            File file2 = new File(serverDir + "\\" + renameFile.getNewFileName());
//            file1.renameTo(file2);
//            ctx.writeAndFlush(new ListMessage(serverDir));
//        } else if (cloudMessage instanceof Pass pass){
//            System.out.println("pass " + pass.getLogin());
//            System.out.println("login " + pass.getPassword());
//            if (auth(pass.getLogin(), pass.getPassword())) pass.setIn(true);
//            ctx.writeAndFlush(pass);
//            System.out.println(" pass "+ pass.isIn());
//        } else if (cloudMessage instanceof DirectoryMessage directoryMessage) {
//            System.out.println("folder " + serverDir.resolve(Path.of(directoryMessage.getFileName())));
//            Files.createDirectory(serverDir.resolve(Path.of(directoryMessage.getFileName())));
//        }
    }

    public boolean auth (String login, String pass) {
            try (PreparedStatement statement = connection.prepareStatement
                    ("SELECT * FROM database WHERE login = ? AND password = ?;")) {
                statement.setString(1, login);
                statement.setString(2, pass);
                ResultSet resultSet = statement.executeQuery();
                boolean result = resultSet.isClosed();
                System.out.println("bd " + result);
                return !result;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        return false;
    }
}
