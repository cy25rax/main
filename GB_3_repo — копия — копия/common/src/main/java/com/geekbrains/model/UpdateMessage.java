package com.geekbrains.model;

import java.io.IOException;

public class UpdateMessage implements CloudMessage {

    private final String fileName;

    public UpdateMessage(String name) throws IOException {
        this.fileName = name;
    }

    @Override
    public MessageType getType() {
        return MessageType.UPDATE_MESSAGE;
    }

    public String getName() {
        return fileName;
    }

    public Object getFileName() {
        return fileName;
    }
}
