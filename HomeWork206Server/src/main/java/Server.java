import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
        public static void main(String[] args) {
            Socket socket = null;
            try (ServerSocket serverSocket = new ServerSocket(8189)) {
                System.out.println("Сервер запущен, ожидаем подключения...");
                socket = serverSocket.accept();
                System.out.println("Клиент подключился");
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                Scanner scanner = new Scanner(System.in);


                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while (true){
                                String str = scanner.nextLine();
                                if ("/end".equals(str)) {
                                    break;
                                }
                                out.writeUTF("сервер прислал: " +str);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();


                while (true) {
                    String str = in.readUTF();

                    if ("/end".equals(str)) {
                        break;
                    }
                    out.writeUTF("Эхо: " +str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

}
