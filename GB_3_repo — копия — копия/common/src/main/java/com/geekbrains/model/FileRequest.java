package com.geekbrains.model;

public class FileRequest implements CloudMessage {

    private final String fileName;

    public FileRequest(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public MessageType getType() {
        return MessageType.FILE_REQUEST;
    }

    public String getFileName() {
        return fileName;
    }

}
