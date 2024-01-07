package org.example.gb.lesson5.hw;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class ServerApp {
    public static final int PORT = 8080;

    private static long clientIdCounter = 1L;
    private static final AuthService authService = new SimpleAuthService();
    private static Map<Long, Client> clients = new HashMap<>();

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен на порту " + PORT);
            while (true) {
                final Socket clientSocket = server.accept();
                final long clientId = clientIdCounter++;
                Client newChatClient = new Client(clientId, clientSocket);

                System.out.println("Подключился новый клиент[" + newChatClient + "]");
                clients.put(clientId, newChatClient);


                new Thread(() -> {
                    try (Scanner input = newChatClient.getInput();
                         PrintWriter output = newChatClient.getOutput()) {
                        newChatClient.getOutput().println("введите логин и пароль через пробел");

                        String authString = input.nextLine();
                        Role newChatClientRole = authService.checkLoginPassword(authString);
                        if (newChatClientRole == Role.None) {
                            newChatClient.getOutput().println("не верные логин и пароль");
                            newChatClient.getSocket().close();
                            Thread.currentThread().interrupt();
                        } else {
                            newChatClient.setRole(newChatClientRole);
                        }

                        output.println("Подключение успешно. Список всех клиентов: " + clients);

                        while (true) {
                            String clientInput = input.nextLine();
                            if (Objects.equals("q", clientInput)) {
                                // todo разослать это сообщение всем остальным клиентам
                                clients.remove(clientId);
                                clients.values().forEach(it -> it.getOutput().println("Клиент[" + clientId + "] отключился"));
                                break;
                            }
                            
                            if (!clientInput.startsWith("@") && !clientInput.startsWith("kick")) {
                                broadcastMessage(clientInput, clientId);
                            } else if (clientInput.startsWith("kick") && newChatClientRole == Role.Admin) {
                                System.out.println("admin");
                                kickClient(clientInput);
                            } else {
                                privateMessage(clientInput);
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            }
        }
    }

    private static void kickClient(String clientInput) {
        Long kickClientID = Long.valueOf(clientInput.split(" ")[1]);

        System.out.println(clients);
        if (clients.containsKey(kickClientID)) {
            Client kickClient = clients.get(kickClientID);
            try {
                kickClient.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            clients.remove(kickClientID);
        }

        System.out.println(clients);
    }

    private static void privateMessage(String clientInput) {
        Long privateMessageID = Long.valueOf(clientInput.split(" ")[0].substring(1));
        if (clients.containsKey(privateMessageID)) {
            Client destinationClient = clients.get(privateMessageID);
            destinationClient.getOutput().println(clientInput);
        }
    }

    private static void broadcastMessage(String clientInput, long clientId) {
        for (Client client : clients.values()) {
            if (client.getId() != clientId) {
                client.getOutput().println(clientInput);
            }
        }
    }
}
