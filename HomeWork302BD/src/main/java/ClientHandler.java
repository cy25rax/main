import java.io.*;
import java.net.Socket;

public class ClientHandler {
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
            new Thread(() -> {
                try {
                    authentication();
                    file = new File("logfile_"+name+".txt");
                    file2 = new File("logfile_"+name+"STREAM.txt");
                    fileWriter = new FileWriter(file,true);
                    fileOutputStream = new FileOutputStream(file2,true);
                    readMessages();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    closeConnection();
                    try {
                        fileOutputStream.close();
                        fileWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (IOException e) {
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
            fileWriter.write(strFromClient+"\n");
            fileWriter.flush();
            String str= strFromClient+"\n";
            byte[] bytes = str.getBytes();
            fileOutputStream.write(bytes);

            System.out.println("от " + name + ": " + strFromClient);
            if (strFromClient.equals("/end")) {
                return;
            }

            if (strFromClient.startsWith("/w")) {
                String[] privatTextNick = strFromClient.split("\\s");

                strFromClient = strFromClient.replaceFirst("/w ","");
                strFromClient = strFromClient.replaceFirst(privatTextNick[1]+ " ", "");

                sendMsg("privat msg to :" + privatTextNick[1] +" "+ strFromClient);
                myServer.privatMsg(name + " личное сообщение: " + strFromClient,privatTextNick[1]);
                continue;
            }

            if (strFromClient.startsWith("/rnm")) {
                strFromClient = strFromClient.replaceFirst("/rnm ","");
                myServer.getAuthService().updateEx(this.name, strFromClient);
                this.name=strFromClient;
                fileWriter.close();
                file.renameTo(new File("logfile_"+strFromClient+".txt"));
                fileWriter = new FileWriter("logfile_"+strFromClient+".txt");
                file2.renameTo(new File("logfile_"+strFromClient+".txt"));
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
            e.printStackTrace();
        }
    }
    public void closeConnection() {
        myServer.unsubscribe(this);
        myServer.broadcastMsg(name + " вышел из чата");
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
}