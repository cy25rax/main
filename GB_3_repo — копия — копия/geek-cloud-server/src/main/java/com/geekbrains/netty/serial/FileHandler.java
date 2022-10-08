package com.geekbrains.netty.serial;

import com.geekbrains.model.*;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FileHandler extends SimpleChannelInboundHandler<CloudMessage> {

    private Path serverDir;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        serverDir = Path.of("server_files");
        ctx.writeAndFlush(new ListMessage(serverDir));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CloudMessage cloudMessage) throws Exception {
        System.out.println("mess " + cloudMessage.getType());
        if (cloudMessage instanceof FileMessage fileMessage) {
            System.out.println("Filemessage");
            Files.write(serverDir.resolve(fileMessage.getFileName()), fileMessage.getBytes());
            ctx.writeAndFlush(new ListMessage(serverDir));
        } else if (cloudMessage instanceof FileRequest fileRequest) {
            System.out.println("Filemessage reqvest");
            ctx.writeAndFlush(new FileMessage(serverDir.resolve(fileRequest.getFileName())));
        } else if (cloudMessage instanceof UpdateMessage updateMessage) {
            System.out.println("Filemessage updatre");
            if (Files.isDirectory(Path.of(String.valueOf(serverDir.resolve(updateMessage.getName()))))){
                serverDir = Path.of(serverDir + "\\" + updateMessage.getFileName());
                ctx.writeAndFlush(new ListMessage(serverDir));
            }
        } else if (cloudMessage instanceof DeleteFile deleteFile) {
            System.out.println("Filemessage delete");
            Files.delete(Path.of(serverDir + "\\" + deleteFile.getFileName()));
            ctx.writeAndFlush(new ListMessage(serverDir));
        } else if (cloudMessage instanceof RenameFile renameFile) {
            System.out.println("Filemessage rename");
            File file1 = new File(serverDir + "\\" + renameFile.getOldFileName());
            File file2 = new File(serverDir + "\\" + renameFile.getNewFileName());
            file1.renameTo(file2);
            ctx.writeAndFlush(new ListMessage(serverDir));
        } else if (cloudMessage instanceof Pass pass){
            System.out.println("pass " + pass.getLogin());
            System.out.println("login " + pass.getPassword());
        }
    }

    public int Auth(String login, String pass) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:javadb.db")) {
            System.out.println("connection ");
            try (PreparedStatement statement = connection.prepareStatement
                    ("SELECT * FROM database WHERE login = ? AND password = ?;")) {
                statement.setString(1, login);
                statement.setString(2, pass);
                ResultSet resultSet = statement.executeQuery();
                Boolean result = resultSet.isClosed();
                if (result) return 1;
                else return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
