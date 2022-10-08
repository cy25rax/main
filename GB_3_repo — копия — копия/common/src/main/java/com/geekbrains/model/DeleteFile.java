package com.geekbrains.model;

public class DeleteFile implements CloudMessage {

    private String fileName;

    public DeleteFile(String fileName) {
        this.fileName=fileName;
    }

    @Override
    public MessageType getType() {
        return MessageType.DELETE_FILE;
    }

    public Object getFileName() {
        return fileName;
    }

}
