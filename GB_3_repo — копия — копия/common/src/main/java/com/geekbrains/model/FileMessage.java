package com.geekbrains.model;

import java.io.IOException;
import java.nio.file.Path;

public class FileMessage implements CloudMessage {

    private final String fileName;
    private final byte[] bytes;
    private final String  path;

    public FileMessage(Path file, Path shortPath, byte[] b) throws IOException {
        fileName = file.getFileName().toString();
        bytes = b;
        path = shortPath.toString();
    }

    public String  getPath() {
        return path;
    }

    @Override
    public MessageType getType() {
        return MessageType.FILE;
    }

    public String getName() {
        return this.fileName;
    }

    public byte[] getBytes() {
        return bytes;
    }
}
