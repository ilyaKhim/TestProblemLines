package Client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private DataInputStream in;
    private DataOutputStream out;

    public Client() throws IOException, InterruptedException {
        connect();
    }

    public void sentMsg()  {
        try {
            out.writeUTF("testMsg from client");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getCoordinates() throws IOException {
        String line;
        while ((line = in.readUTF()) != null){
            System.out.println(line);
        }
    }

    public void connect() throws IOException {

        try{
            String IP_ADRESS = "localhost";
            int PORT = 29288;
            Socket socket = new Socket(IP_ADRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            System.out.println("Я подключился");
            sentMsg();
            String str = in.readUTF();
            System.out.println(str);
            getCoordinates();






        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
