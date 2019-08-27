package Client;

import Server.SeparateFile;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

class Client {

    private List<SeparateFile> objects = new ArrayList<>();

    public Client() {
        connect();
    }

    List<SeparateFile> getObjects() {
        return objects;
    }


    private void catchObject(Socket socket, JFrame component) {
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            ObjectInputStream ois = new ObjectInputStream(in);
            try {
                while (true) {
                    SeparateFile file = (SeparateFile) ois.readObject();
                    objects.add(file);
                    SwingUtilities.updateComponentTreeUI(component);

                }
            } catch (EOFException e) { // значит файл закончился, нужно его "выплюнуть"
                System.out.println("Конец передачи.");
            }

        } catch (IOException e) {
            System.out.println("Ошибка потока ввода.");
        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка поиска класса. Такого класса не существует.");
        }
    }

    private void connect() {

        try {
            String IP_ADRESS = "localhost";
            int PORT = 29288;
            final Socket socket = new Socket(IP_ADRESS, PORT);

            System.out.println("Я подключился");
            final LinesComponent linesComponent = new LinesComponent(this);
            linesComponent.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            Runnable runnable =
                    new Runnable() {
                        public void run() {
                            System.out.println("Runnable running");
                            catchObject(socket, linesComponent);
                        }
                    };
            Thread thread = new Thread(runnable);
            thread.start();


        } catch (UnknownHostException e) {
            System.out.println("Ошибка поиска хостинга");
        } catch (IOException e) {
            System.out.println("Ошибка потока вывода.");
        }
    }
}
