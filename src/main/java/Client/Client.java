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

public class Client extends JFrame {

    public Client() throws IOException{

        connect();

    }

    public static ArrayList<Line2D> makeAllLines(ArrayList<SeparateFile>files){
        ArrayList<Line2D> arrayList = new ArrayList<>();
        for (int i = 0; i<files.size()-1; i++){
            arrayList.add(new Line2D.Double(files.get(i).getX(), files.get(i).getY(), files.get(i+1).getX(), files.get(i+1).getY()));
        }
        return arrayList;
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
            ArrayList<SeparateFile> files = catchObject(socket);
            makeAllLines(files);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
