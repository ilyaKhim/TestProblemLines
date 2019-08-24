package Client;

import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.String.format;

public class SeparateFile {
    public String fileName = "C:\\Users\\1\\IdeaProjects\\TestProblemLines\\src\\main\\java\\Server\\Coordinates.txt";
    public String ID;
    public String command;
    public String x;
    public String y;
    public String color;
    private String[] fields  = {ID,command,x,y,color};

    public SeparateFile(ArrayList<String> arr){
        this.command = arr.get(1);
        this.x = arr.get(2);
        this.y = arr.get(3);
        this.color = arr.get(4);
    }

    private static SeparateFile separateFile(String line){
        StringTokenizer token = new StringTokenizer(line,"\n");
        int i = 0;
        while (token.hasMoreElements()){
            return new SeparateFile(separateLine(token.nextToken()));
        }
        return null;
    }

    private static ArrayList<String> separateLine(String line){
        StringTokenizer token = new StringTokenizer(line,";");
        ArrayList<String> arrayList = new ArrayList<>();
        while (token.hasMoreElements()){
            arrayList.add(String.valueOf(token.nextElement()));
        }
        return arrayList;
    }

    public boolean checkMac(String macFromFile) {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            InetAddress address = InetAddress.getLocalHost();
            System.out.println(address);
            NetworkInterface ni = NetworkInterface.getByInetAddress(address);
            System.out.println(ni);
            if (ni != null) {
                byte[] mac = ni.getHardwareAddress();
                if (mac != null) {
                    for (int i = 0; i < mac.length; i++) {
                        String str = String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : "");
                        stringBuffer.append(str);
                    }
                    if (stringBuffer.toString().equals(macFromFile)) return true;


                } else {
                    System.out.println("Address doesn't exist or is not accessible.");
                    return false;
                }
            } else {
                System.out.println("Network Interface for the specified address is not found.");
                return false;
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return false;
    }


    private String getFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        StringBuffer str = new StringBuffer();
        while((line = reader.readLine()) != null){
            str.append(line+"\n");
        }

        return str.toString();
    }


    public static void main(String[] args) throws IOException {
        SeparateFile file = separateFile("60:21:C0:2A:E0:33;move;0.19934128;0.686075;-16777216");
        System.out.println(file.x);
        System.out.println(file.y);
        System.out.println(file.color);
        System.out.println(file.command);
        System.out.println(file.checkMac(file.ID));
    }
}
