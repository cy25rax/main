package com.geekbrains.model;

public class RenameFile implements CloudMessage {

    private String oldFileName;
    private String newFileName;

    public String getOldFileName() {
        return oldFileName;
    }

    public String getNewFileName() {
        return newFileName;
    }

    public RenameFile(String oldFileName, String newFileName) {
        this.oldFileName = oldFileName;
        this.newFileName = newFileName;
    }

    @Override
    public MessageType getType() {
        return MessageType.RENAME_FILE;
    }
}