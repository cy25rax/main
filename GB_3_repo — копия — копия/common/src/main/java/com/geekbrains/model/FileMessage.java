package com.geekbrains.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileMessage implements CloudMessage {

    private final String fileName;

    private final long size;

    private final byte[] bytes;
    private final String  path;
    public FileMessage(Path file, Path shortPath) throws IOException {
        fileName = file.getFileName().toString();
        bytes = Files.readAllBytes(file.toAbsolutePath());
        size = bytes.length;
        path = shortPath.toString();
    }

    public String  getPath() {
        return path;
    }

    public long getSize() {
        return size;
    }

    @Override
    public MessageType getType() {
        return MessageType.FILE;
    }

    public String getName() {
        System.out.println("file name" + fileName);
        return this.fileName;
    }

    public byte[] getBytes() {
        return bytes;
    }
}
