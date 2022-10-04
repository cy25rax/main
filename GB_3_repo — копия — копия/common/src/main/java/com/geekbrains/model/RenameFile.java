package com.geekbrains.model;

import lombok.Getter;

        import java.io.IOException;
        import java.nio.file.Files;
        import java.nio.file.Path;

@Getter
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