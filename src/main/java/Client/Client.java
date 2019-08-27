package Client;

import Server.SeparateFile;

import javax.swing.*;
import java.awt.geom.Line2D;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Client {

    private ArrayList<SeparateFile>objects;

    public ArrayList<SeparateFile> getObjects() {
        return objects;
    }


    public void setObjects(ArrayList<SeparateFile> files) {
        this.objects = files;
    }

    public Client() throws IOException{
        connect();


    }





    public ArrayList<SeparateFile> catchObject(Socket socket){
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
            setObjects(catchObject(socket)); // список объектов со всеми полями, которые мы получили от сервера мы привязываем
            // к конкретному объетку, чтобы достать из объекта точки
            new LinesComponent(this);



        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
