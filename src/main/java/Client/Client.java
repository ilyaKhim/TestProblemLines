package Client;

import Server.SeparateFile;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

class Client {

    private ArrayList<SeparateFile>objects;

    Client(){
        connect();
    }

    ArrayList<SeparateFile> getObjects() {
        return objects;
    }

    private void setObjects(ArrayList<SeparateFile> files) {
        this.objects = files;
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
            } catch (EOFException e){ // значит файл закончился, нужно его "выплюнуть"
                return files;
            }

        } catch (IOException e) {
            System.out.println("Ошибка потока ввода.");
        } catch (ClassNotFoundException e){
            System.out.println("Ошибка поиска класса. Такого класса не существует.");
        }
        return null;
    }

    private void connect(){

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
            System.out.println("Ошибка поиска хостинга");
        } catch (IOException e){
            System.out.println("Ошибка потока вывода.");
        }
    }
}
