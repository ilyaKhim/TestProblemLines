package Client;

import Server.SeparateFile;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;

public class Client {
    private DataInputStream in;
    private DataOutputStream out;

    public Client() throws IOException{
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
        try{
            while ((line = in.readUTF()) != null){
                System.out.println(line);
            }
        } catch (EOFException e){
            return;
        }
    }

    private SeparateFile catchObject(){
        try{
            SeparateFile file;
            ObjectInputStream ois = new ObjectInputStream(in);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(ois));
            bufferedReader.readLine();
            while (bufferedReader.ready()){
                while (in.available()!=0){
                    file = (SeparateFile)ois.readObject();
                    System.out.println(file);
                }
            }
            ois.close();

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
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            System.out.println("Я подключился");
            sentMsg();
            String str = in.readUTF();
            System.out.println(str);
            getCoordinates();
            catchObject();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
