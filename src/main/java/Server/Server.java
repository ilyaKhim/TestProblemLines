package Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import static Server.SeparateFile.getFile;
import static Server.SeparateFile.separateFile;

class Server {


    private static final int STEP_IN_MILLISECONDS = 200;

    Server(String fileName)  {
        ServerSocket server = null;
        Socket socket = null;
        try{
            server = new ServerSocket(29288);
            System.out.println("Сервер запущен");
            ArrayList<SeparateFile> files = separateFile(getFile(fileName));
            while (true){
                socket = server.accept();
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                System.out.println("Клиент подключился.");
                sendObjects(files, out);
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try{
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e){
                System.out.println("Ошибка закрытия сокета.");
            }
            try{
                if (server != null) {
                    server.close();
                }
            } catch (IOException e){
                System.out.println("Ошибка закрытия сервера.");
            }
        }

    }

    private void sendObjects(ArrayList<SeparateFile> files, DataOutputStream out){
        {
            try(ObjectOutputStream ous = new ObjectOutputStream(out)){
                for(SeparateFile file: files){
                    if( file.getColorEquality()) // при несовпадении мак-адресов объект не будет передан и ничего не нарисуется
                    {
                        Thread.sleep(STEP_IN_MILLISECONDS);
                        ous.writeObject(file);
                    }
                }
                System.out.println("Files has been written");
            } catch (IOException | InterruptedException e) {
                System.out.println("Ошибка потока вывода.");
            }
        }
    }
}
