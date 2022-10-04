package com.geekbrains.model;

import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Getter
public class DeleteFile implements CloudMessage {

    private String fileName;

    public DeleteFile(String fileName) {
        this.fileName=fileName;
    }

    @Override
    public MessageType getType() {
        return MessageType.DELETE_FILE;
    }
}
