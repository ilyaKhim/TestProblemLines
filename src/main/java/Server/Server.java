package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    BufferedReader in;
    BufferedWriter out;

    public Server()  {
        ServerSocket server = null;
        Socket socket = null;
        try{
            server = new ServerSocket(5555);
            System.out.println("Сервер запущен");
            while (true){
                socket = server.accept();
                catchTest();
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));;
                System.out.println("Клиент подключился.");
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

    public void catchTest() throws IOException {
        System.out.println(in.read());
    }
}
