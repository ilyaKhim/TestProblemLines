package Client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    Socket socket;
    private final String IP_ADRESS = "localhost";
    private final int PORT = 5555;
    BufferedReader in;
    BufferedWriter out;

    public Client() throws IOException {
        connect();
    }

    public void sentTest() throws IOException {
        out.write("testOutFromClient");
        out.flush();
    }

    public void connect() throws IOException {

        try{
            socket = new Socket(IP_ADRESS, PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            System.out.println("Я подключился");


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
