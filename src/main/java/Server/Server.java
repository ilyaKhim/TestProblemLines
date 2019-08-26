package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import static Server.SeparateFile.getFile;
import static Server.SeparateFile.separateFile;

public class Server {


    private String fileName = "C:\\Users\\1\\IdeaProjects\\TestProblemLines\\src\\main\\java\\Server\\Coordinates.txt";

    public void sendObjects(ArrayList<SeparateFile> files, DataOutputStream out){
        {
            try(ObjectOutputStream ous = new ObjectOutputStream(out)){
                for(SeparateFile file: files){
                    ous.writeObject(file);
                }
                System.out.println("Files has been written");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Server()  {
        ServerSocket server = null;
        Socket socket = null;
        try{
            server = new ServerSocket(29288);
            System.out.println("Сервер запущен");
            ArrayList<SeparateFile> files = separateFile(getFile(fileName));

            // TODO: 25.08.2019 Необходимо организовать отправку сообщений клиенту при его подключении.
            // todo: клиент получает точки и рисует без участия человека
            while (true){
                socket = server.accept();
                //DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                System.out.println("Клиент подключился.");
                sendObjects(files, out);

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
}
