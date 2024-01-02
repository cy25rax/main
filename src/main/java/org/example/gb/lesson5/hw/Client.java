package org.example.gb.lesson5.hw;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

@Getter
public class Client implements AutoCloseable {
    private final long id;
    private final Socket socket;
    private final Scanner input;
    private final PrintWriter output;
    @Setter
    private Role role;

    Client(long id, Socket socket) throws IOException {
        this.id = id;
        this.socket = socket;
        this.input = new Scanner(socket.getInputStream());
        this.output = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void close() throws Exception {
        socket.close();
    }

    @Override
    public String toString() {
        return String.format("%s", socket.getInetAddress().toString());
    }
}
