import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
        private final String SERVER_ADDR = "localhost";
        private final int SERVER_PORT = 8189;
        private Socket socket;
        private DataInputStream in;
        private DataOutputStream out;

        public void openConnection() throws IOException {
            socket = new Socket(SERVER_ADDR, SERVER_PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String strFromServer = in.readUTF();
                            if ("/end".equalsIgnoreCase(strFromServer)) {
                                break;
                            }
                            System.out.println(strFromServer);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        closeConnection();
                    }
                }
            }).start();
        }
        public void closeConnection() {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) {
            Client client = new Client();
            client.start();
        }

    private void start() {
        try {
            openConnection();
            Scanner scanner = new Scanner(System.in);
            while (true){
                String msg = scanner.nextLine();
                if ("/end".equalsIgnoreCase(msg)) {
                    break;
                }
                out.writeUTF(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}