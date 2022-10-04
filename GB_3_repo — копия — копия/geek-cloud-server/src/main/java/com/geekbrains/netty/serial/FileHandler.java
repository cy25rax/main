package com.geekbrains.netty.serial;

import com.geekbrains.model.*;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class FileHandler extends SimpleChannelInboundHandler<CloudMessage> {

    private Path serverDir;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        serverDir = Path.of("server_files");
        ctx.writeAndFlush(new ListMessage(serverDir));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CloudMessage cloudMessage) throws Exception {
        log.debug("Received: {}", cloudMessage.getType());
        if (cloudMessage instanceof FileMessage fileMessage) {
            Files.write(serverDir.resolve(fileMessage.getFileName()), fileMessage.getBytes());
            ctx.writeAndFlush(new ListMessage(serverDir));
        } else if (cloudMessage instanceof FileRequest fileRequest) {
            ctx.writeAndFlush(new FileMessage(serverDir.resolve(fileRequest.getFileName())));
        } else if (cloudMessage instanceof UpdateMessage updateMessage) {
            if (Files.isDirectory(Path.of(String.valueOf(serverDir.resolve(updateMessage.getName()))))){
                serverDir = Path.of(serverDir + "\\" + updateMessage.getFileName());
                ctx.writeAndFlush(new ListMessage(serverDir));
            }
        } else if (cloudMessage instanceof DeleteFile deleteFile) {
            Files.delete(Path.of(serverDir + "\\" + deleteFile.getFileName()));
            ctx.writeAndFlush(new ListMessage(serverDir));
        } else if (cloudMessage instanceof RenameFile renameFile) {
            File file1 = new File(serverDir + "\\" + renameFile.getOldFileName());
            File file2 = new File(serverDir + "\\" + renameFile.getNewFileName());
            file1.renameTo(file2);
            ctx.writeAndFlush(new ListMessage(serverDir));
        }
    }
}