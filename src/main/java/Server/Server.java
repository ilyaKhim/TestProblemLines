package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Server {

    private DataInputStream in;
    private DataOutputStream out;
    private String fileName = "C:\\Users\\1\\IdeaProjects\\TestProblemLines\\src\\main\\java\\Server\\Coordinates.txt";

    public Server()  {
        ServerSocket server = null;
        Socket socket = null;
        try{
            server = new ServerSocket(29288);
            System.out.println("Сервер запущен");
            while (true){
                socket = server.accept();
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
                System.out.println("Клиент подключился.");
                catchMsg();
                sendMsg();
                sendCoordinates();

            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try{
                socket.close();
            } catch (IOException e){
                e.printStackTrace();
            }
            try{
                server.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    private void sendMsg(){
        try {
            out.writeUTF("msg from server");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void sendCoordinates() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while((line = reader.readLine()) != null){

            out.writeUTF(line);
        }
    }

    private void catchMsg() throws IOException {
        System.out.println(in.readUTF());
    }
}
