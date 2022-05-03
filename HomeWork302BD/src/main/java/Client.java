import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client{
    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8189;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private boolean loggedIn =false;
    private String msg;
    Gui gui ;

    public void openConnection() throws IOException {
        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        gui = new Gui();

        new Thread(() -> {
            try {
                Thread.sleep(50000);
                if (!loggedIn) {
                    System.out.println("попробуйте в следующий раз");
                    closeConnection();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();

        new Thread(() -> {
            try {
                while (true) {
                    String strFromServer = in.readUTF();

                    if (strFromServer.equalsIgnoreCase("/end")) {
                        break;
                    }

                    if (strFromServer.startsWith("/authok")) {
                        loggedIn=true;
                        gui.isLoggedIn(true);
                        Thread.sleep(2000);
                    }

                    if (loggedIn) gui.setText(strFromServer);
                    System.out.println(strFromServer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                closeConnection();
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
            while (true){
                msg ="";

                if (gui.getSend()) {
                    msg = gui.getText();
                    gui.setSend(false);
                }

                if ("/end".equalsIgnoreCase(msg)) {
                    out.writeUTF(msg);
                    break;
                }

                if (msg.length()>0) {
                    out.writeUTF(msg);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}