package org.example.gb.lesson5.hw;

import org.example.gb.lesson5.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class ClientApp {
    public static void main(String[] args) throws IOException {
        final Socket client = new Socket("localhost", ServerApp.PORT);
        // чтение
        new Thread(() -> {
            try (Scanner input = new Scanner(client.getInputStream())) {
                while (true) {
                    System.out.println(input.nextLine());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();

        // запись
        new Thread(() -> {
            try (PrintWriter output = new PrintWriter(client.getOutputStream(), true)) {
                Scanner consoleScanner = new Scanner(System.in);
                while (true) {
                    String consoleInput = consoleScanner.nextLine();
                    if (Objects.equals("q", consoleInput)) {
                        client.close();
                        break;
                    }
                    output.println(consoleInput);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

    }

}

