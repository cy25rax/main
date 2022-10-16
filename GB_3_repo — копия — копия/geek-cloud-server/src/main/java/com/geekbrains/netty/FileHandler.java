package com.geekbrains.netty;

import com.geekbrains.model.*;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FileHandler extends SimpleChannelInboundHandler<CloudMessage> {

    private Path serverDir;
    private Lock lock = new ReentrantLock();
    private boolean isPass;
    private static List<Path> paths;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        serverDir = Path.of("server_files").toAbsolutePath();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CloudMessage cloudMessage) throws Exception {
        switch (cloudMessage.getType()){
            case FILE -> {
                FileMessage fileMessage = (FileMessage) cloudMessage;
                System.out.println("file name_" + fileMessage.getName());
                if (fileMessage.getPath().equals("")){
                    Files.write(serverDir.resolve(fileMessage.getName()),
                            fileMessage.getBytes(),
                            StandardOpenOption.CREATE,
                            StandardOpenOption.APPEND);
                } else {
                    if (Files.exists(serverDir.resolve(fileMessage.getPath()).getParent())){
                        Files.write(serverDir.resolve(fileMessage.getPath()),
                                fileMessage.getBytes(),
                                StandardOpenOption.CREATE,
                                StandardOpenOption.APPEND);
                    } else {
                        Files.createDirectories(serverDir.resolve(fileMessage.getPath()).getParent());
                        Files.write(serverDir.resolve(fileMessage.getPath()),
                                fileMessage.getBytes(),
                                StandardOpenOption.CREATE,
                                StandardOpenOption.APPEND);
                    }
                }
                ctx.writeAndFlush(new ListMessage(serverDir));
            }
            case LIST -> {

            }
            case FILE_REQUEST -> {
                FileRequest fileRequest = (FileRequest) cloudMessage;
                if (!Files.isDirectory(Path.of(serverDir.toString()).resolve(fileRequest.getFileName()))){
                    sendFile(fileRequest.getFileName(), "", ctx);

//            если отправлям папку то отправляем все файлы в папке
                } else {
                    paths = new ArrayList<>();
                    Path myPath = serverDir;
                    paths.add(serverDir.resolve(fileRequest.getFileName()));
                    var paths = walk(Path.of(myPath + "\\" + fileRequest.getFileName()));
                    paths.forEach(path -> {
                        try {
                            if (Files.isDirectory(path)) {
                                ctx.writeAndFlush(
                                        new DirectoryMessage(myPath.relativize(path)
                                                , myPath));
                            } else {
                                sendFile(path.toAbsolutePath().toString(), (myPath.relativize(path)).toString(), ctx);
                            }
                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
            case DIRECTORY -> {
                DirectoryMessage directoryMessage = (DirectoryMessage) cloudMessage;
                Files.createDirectories(serverDir.resolve(directoryMessage.getFileName()));
                ctx.writeAndFlush(new ListMessage(serverDir));
            }
            case LOGIN_PASSWORD -> {
                LoginAndPass pass = (LoginAndPass) cloudMessage;
                isPass = Auth.auth(pass.getLogin(), pass.getPassword());
                ctx.writeAndFlush(new Pass(isPass));
                if (isPass) ctx.writeAndFlush(new ListMessage(serverDir));
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
    }

    private void sendFile(String fileName,String fileNameParent, ChannelHandlerContext ctx) throws IOException, InterruptedException {
        lock.lock();
        byte[] byteArray = new byte[1024*32];
        File file = Path.of(serverDir.toString()).resolve(fileName).toFile();
        FileInputStream fis = new FileInputStream(file.getPath());
        long s = file.length();
        if (s == 0) {
            ctx.writeAndFlush(
                    new FileMessage(serverDir.resolve(fileName),
                            Path.of(fileNameParent),
                            fis.readAllBytes())
            );
        }
        while (s > 0) {
            int i = fis.read(byteArray);
            ctx.writeAndFlush(
                    new FileMessage(serverDir.resolve(fileName),
                            Path.of(fileNameParent),
                            byteArray)
            );
            s-= i;
        }
        fis.close();
        lock.unlock();
    }

    private List<Path> walk(Path path) throws IOException {
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
}
