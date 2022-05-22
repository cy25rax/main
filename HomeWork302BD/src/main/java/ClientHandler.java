import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientHandler extends Logger {

    private MyServer myServer;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private FileWriter fileWriter;
    private File file;
    private File file2;
    private FileOutputStream fileOutputStream;
    private String name;
    public String getName() {
        return name;
    }
    public ClientHandler(MyServer myServer, Socket socket) {
        try {
            this.myServer = myServer;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.name = "";
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(() -> {
//            new Thread(() -> {
                try {
                    authentication();
//                    file = new File("logfile_"+name+".txt");
//                    file2 = new File("logfile_"+name+"STREAM.txt");
//                    fileWriter = new FileWriter(file,true);
//                    fileOutputStream = new FileOutputStream(file2,true);
//                    throw new RuntimeException("asd");
                    readMessages();
                } catch (IOException e) {
                    getErrorLog().warn(e);
                    e.printStackTrace();
                } finally {
                    closeConnection();
                }
//            }).start();
            });
            executorService.shutdown();
        } catch (IOException e) {
            getErrorLog().warn("Проблемы при создании обработчика клиента");
            throw new RuntimeException("Проблемы при создании обработчика клиента");
        }

    }
    public void authentication() throws IOException {
        while (true) {
            String str = in.readUTF();
            if (str.startsWith("/auth")) {

                String[] parts = str.split("\\s");
                String nick =
                        myServer.getAuthService().getNickByLoginPass(parts[1], parts[2]);
                if (nick != null) {
                    if (!myServer.isNickBusy(nick)) {
                        sendMsg("/authok " + nick);
                        getLog().info("пользователь " + nick + " авторизовался");
                        name = nick;
                        myServer.broadcastMsg(name + " зашел в чат");
                        myServer.subscribe(this);
                        return;
                    } else {
                        sendMsg("Учетная запись уже используется");
                    }
                } else {
                    sendMsg("Неверные логин/пароль");
                }
            }
        }
    }
    public void readMessages() throws IOException {
        while (true) {
            String strFromClient = in.readUTF();
//            fileWriter.write(strFromClient+"\n");
//            fileWriter.flush();
//            String str= strFromClient+"\n";
//            byte[] bytes = str.getBytes();
//            fileOutputStream.write(bytes);

            getLog().info("пользователь " + name + " отправил сообщение : "+strFromClient);
            if (strFromClient.startsWith("/end")) {
                break;
            }

            if (strFromClient.startsWith("/w")) {
                String[] privatTextNick = strFromClient.split("\\s");

                strFromClient = strFromClient.replaceFirst("/w ","");
                strFromClient = strFromClient.replaceFirst(privatTextNick[1]+ " ", "");

                getLog().info("пользователь " + name + " отправил личное сообщение сообщение клиенту" +
                          privatTextNick[1] + ": "+strFromClient);
                sendMsg("privat msg to :" + privatTextNick[1] +" "+ strFromClient);
                myServer.privatMsg(name + " личное сообщение: " + strFromClient,privatTextNick[1]);
                continue;
            }

            if (strFromClient.startsWith("/rnm")) {
                strFromClient = strFromClient.replaceFirst("/rnm ","");
                myServer.getAuthService().updateEx(this.name, strFromClient);
                this.name=strFromClient;
//                fileWriter.close();
//                file.renameTo(new File("logfile_"+strFromClient+".txt"));
//                fileWriter = new FileWriter("logfile_"+strFromClient+".txt");
//                file2.renameTo(new File("logfile_"+strFromClient+".txt"));
                getLog().info("пользователь " + name + " сменил ник на : "+strFromClient);
                myServer.broadcastMsg(name + "- сменил ник на: " + strFromClient);
                continue;
            }

            myServer.broadcastMsg(name + ": " + strFromClient);
        }
    }
    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            getErrorLog().warn(e);
            e.printStackTrace();
        }
    }
    public void closeConnection() {
        getLog().info("пользователь " + name + " вышел из чата");
        myServer.broadcastMsg(name + " вышел из чата");
        myServer.unsubscribe(this);
        try {
            in.close();
            out.close();
            socket.close();
            fileOutputStream.close();
            fileWriter.close();
        } catch (IOException e) {
            getErrorLog().warn(e);
            e.printStackTrace();
        }
    }
}