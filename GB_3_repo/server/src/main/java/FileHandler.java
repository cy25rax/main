import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileHandler implements Runnable {

    private static final String SERVER_DIR = "server_files";
    private static final String SEND_FILE_COMMAND = "file";
    private static final String SHOW_LIST_FILE_COMMAND = "fileList";
    private static final String RECEIVE_FILE_COMMAND = "receiveFile";



    private static final Integer BATCH_SIZE = 256;

    private final Socket socket;

    private final DataInputStream dis;

    private final DataOutputStream dos;

    private byte[] batch;

    public FileHandler(Socket socket) throws IOException {
        this.socket = socket;
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
        batch = new byte[BATCH_SIZE];
        File file = new File(SERVER_DIR);
        if (!file.exists()) {
            file.mkdir();
        }
        System.out.println("Client accepted...");
    }

    @Override
    public void run() {
        try {
            System.out.println("Start listening...");
            while (true) {
                Thread.sleep(100);
                System.out.println("wait command");
                String command = dis.readUTF();
                System.out.println("command = " + command);

                if (command.equals(SEND_FILE_COMMAND)) {
//                    System.out.println("server file catch");
                    String fileName = dis.readUTF();
                    long size = dis.readLong();
                    try (FileOutputStream fos = new FileOutputStream(SERVER_DIR + "/" + fileName)) {
                        for (int i = 0; i < (size + BATCH_SIZE - 1) / BATCH_SIZE; i++) {
                            int read = dis.read(batch);
                            fos.write(batch, 0, read);
                        }
//                        System.out.println("server file end catch ");
                    } catch (Exception ignored) {}
                }

                if (command.equals(SHOW_LIST_FILE_COMMAND)) {

                    List<String> list = getFiles();
//                    System.out.println("server send file list size = " + list.size());
                    dos.writeInt(list.size());
                    for (String str : list) {
                        dos.writeUTF(str);
                        System.out.println(str);
                    }
//                    System.out.println("server end send file list");
                }

                if (command.equals(RECEIVE_FILE_COMMAND)) {

                    System.out.println("start sending file");

                    String fileName = dis.readUTF();

                    System.out.println("file name");

                    File file = new File(SERVER_DIR + "/" + fileName);
                    dos.writeBoolean(file.isFile());

                    System.out.println("is file " + file.isFile());

                    if (file.isFile()) {
                        try {
                            dos.writeLong(file.length());
                            try (FileInputStream fis = new FileInputStream(file)) {
                                byte[] bytes = fis.readAllBytes();
                                dos.write(bytes);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        } catch (Exception e) {
                            System.err.println("e = " + e.getMessage());
                        }
                    }

                    Thread.sleep(100);
                    System.out.println("end sending file");

                }
            }
        } catch (Exception ignored) {
            System.out.println("Client disconnected...");
        }
    }
    private List<String> getFiles() {
        // file.txt 125 b
        // dir [DIR]
        File dir = new File(SERVER_DIR);
        if (dir.isDirectory()) {
            String[] list = dir.list();
            if (list != null) {
                return new ArrayList<>(Arrays.asList(list));
            }
        }
        return List.of();
    }
}
