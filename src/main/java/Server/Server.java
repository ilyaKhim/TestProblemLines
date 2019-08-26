package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import static Server.SeparateFile.getFile;
import static Server.SeparateFile.separateFile;

public class Server {

    private DataInputStream in;
    private DataOutputStream out;
    private String fileName = "C:\\Users\\1\\IdeaProjects\\TestProblemLines\\src\\main\\java\\Server\\Coordinates.txt";

    public void sendObject(ArrayList<SeparateFile> files){
        {
            try(ObjectOutputStream ous = new ObjectOutputStream(out);){
                ous.writeObject(files.get(0));
                System.out.println("File has been written");
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
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
                System.out.println("Клиент подключился.");
                catchMsg();
                sendMsg();
                sendCoordinates();
                sendObject(files);

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
