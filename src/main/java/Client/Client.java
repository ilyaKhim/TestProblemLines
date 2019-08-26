package Client;

import Server.SeparateFile;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Objects;

public class Client {

    public Client() throws IOException{
        connect();
    }

    private ArrayList<SeparateFile> catchObject(Socket socket){
        try{
            ArrayList<SeparateFile> files =  new ArrayList<>();
            DataInputStream in = new DataInputStream(socket.getInputStream());
            ObjectInputStream ois = new ObjectInputStream(in);
            try {
                while (true){
                    files.add((SeparateFile)ois.readObject());
                }
            } catch (EOFException e){
                return files;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void connect(){

        try{
            String IP_ADRESS = "localhost";
            int PORT = 29288;
            Socket socket = new Socket(IP_ADRESS, PORT);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            System.out.println("Я подключился");
            ArrayList<SeparateFile> files = catchObject(socket);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
