package com.geekbrains.model;

import java.io.IOException;
import java.nio.file.Path;

public class DirectoryMessage implements CloudMessage{

    private final String  fileName;
    private final String  startDirectoryName;


    public DirectoryMessage(Path file, Path start) throws IOException {
        fileName = file.toString();
        startDirectoryName = start.toString();
    }

    @Override
    public MessageType getType() {
        return MessageType.DIRECTORY;
    }

    public String  getStartDirectoryName() {
        return startDirectoryName;
    }

    public String getFileName() {
        return this.fileName;
    }

}
