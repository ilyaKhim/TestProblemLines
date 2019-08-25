package Client;

import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;


public class SeparateFile {
    private static final String fileName = "C:\\Users\\1\\IdeaProjects\\TestProblemLines\\src\\main\\java\\Server\\Coordinates.txt";
    private String idEquality;
    private String command;
    private String x;
    private String y;
    private String color;


    private SeparateFile(ArrayList<String> arr){
        this.idEquality = String.valueOf(checkMac(arr.get(0)));
        this.command = arr.get(1);
        this.x = arr.get(2);
        this.y = arr.get(3);
        this.color = arr.get(4);
    }

    private static ArrayList<SeparateFile> separateFile(String line){
        ArrayList<SeparateFile> files = new ArrayList<>();
        StringTokenizer token = new StringTokenizer(line,"\n");
        while (token.hasMoreElements()){
            try {
                files.add(new SeparateFile(separateLine(token.nextToken())));
            } catch (IndexOutOfBoundsException e){ // проверка на случай, если строка не соответствует требованиям.
                try{
                    token.nextToken();
                } catch (NoSuchElementException ex){ // в случае, если неправильная строка была последней
                    return files;
                }
            }
        }
        return files;
    }

    private static ArrayList<String> separateLine(String line){
        StringTokenizer token = new StringTokenizer(line,";");
        ArrayList<String> arrayList = new ArrayList<>();
        while (token.hasMoreElements()){
            arrayList.add(String.valueOf(token.nextElement()));
        }
        return arrayList;
    }

    private static boolean checkMac(String macFromFile) {
        try {
            StringBuilder builder = new StringBuilder();
            InetAddress address = InetAddress.getLocalHost();
            NetworkInterface ni = NetworkInterface.getByInetAddress(address);
            if (ni != null) {
                byte[] mac = ni.getHardwareAddress();
                if (mac != null) {
                    for (int i = 0; i < mac.length; i++) {
                        String str = String.format("%02X%s", mac[i], (i < mac.length - 1) ? ":" : "");
                        builder.append(str);
                    }
                    if (builder.toString().equals(macFromFile)) return true;


                } else {
                    System.out.println("Address doesn't exist or is not accessible.");
                    return false;
                }
            } else {
                System.out.println("Network Interface for the specified address is not found.");
                return false;
            }
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        }
        return false;
    }


    private static String getFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        StringBuilder str = new StringBuilder();
        while((line = reader.readLine()) != null){
            str.append(line).append("\n");
        }

        return str.toString();
    }


    public static void main(String[] args) throws IOException {

        ArrayList<SeparateFile> files = separateFile(getFile(fileName));
        System.out.println(files.get(25).x);
        System.out.println(files.get(25).y);
        System.out.println(files.get(25).color);
        System.out.println(files.get(25).command);
        System.out.println(files.get(25).idEquality);
    }
}
