package com.geekbrains.model;

import java.io.IOException;
import java.nio.file.Path;

public class DirectoryMessage implements CloudMessage{

    private final Path fileName;
    private final Path startDirectoryName;


    public DirectoryMessage(Path file, Path start) throws IOException {
        fileName = file;
        startDirectoryName = start;
    }

    @Override
    public MessageType getType() {
        return MessageType.DIRECTORY;
    }

    public Path getStartDirectoryName() {
        return startDirectoryName;
    }

    public Path getFileName() {
        return this.fileName;
    }

}
