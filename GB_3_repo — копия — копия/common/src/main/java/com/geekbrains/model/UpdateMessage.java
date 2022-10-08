package com.geekbrains.model;

//import lombok.Getter;

import java.io.IOException;

//@Getter
public class UpdateMessage implements CloudMessage {

    private final String fileName;

    public UpdateMessage(String name) throws IOException {
        this.fileName = name;
    }

    @Override
    public MessageType getType() {
        return MessageType.FILE_REQUEST;
    }

    public String getName() {
        return fileName;
    }

    public Object getFileName() {
        return fileName;
    }
}
