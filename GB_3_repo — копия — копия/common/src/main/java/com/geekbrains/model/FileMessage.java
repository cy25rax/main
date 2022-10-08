package com.geekbrains.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileMessage implements CloudMessage {

    private final String  fileName;
    private final long size;
    private final byte[] bytes;

    public FileMessage(Path file) throws IOException {
        fileName = file.getFileName().toString();
        System.out.println(fileName);
        System.out.println("file reed bytes " + file.toAbsolutePath());
        bytes = Files.readAllBytes(file);
        System.out.println(bytes);
        size = bytes.length;
        System.out.println(size);
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
