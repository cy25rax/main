package com.geekbrains.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class ListMessage implements CloudMessage {

    private final List<String> files;

    public ListMessage(Path path) throws IOException {
        this.files = Files.list(path)
                .map(p -> p.getFileName().toString())
                .collect(Collectors.toList());

        if (!path.normalize().equals(Path.of("server_files"))) {
            files.add(0, "..");
        }
    }

    @Override
    public MessageType getType() {
        return MessageType.LIST;
    }

    public List<String> getFiles() {
        return files;
    }
}
