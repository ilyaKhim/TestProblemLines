package Client;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class testseparate {
    String fileName = "C:\\Users\\1\\IdeaProjects\\TestProblemLines\\src\\main\\java\\Server\\Coordinates.txt";

    private ArrayList separateLine(String line){
        ArrayList<String> listOfStrings = new ArrayList<>();
        ArrayList[] arr = new ArrayList[5];
        StringTokenizer token = new StringTokenizer(line,";\n");
        int i = 0;
        while (token.hasMoreElements()){
            if(i==5) i=0;
            String str = token.nextToken();
            listOfStrings.add(str);
            boolean add = arr[i].add(str);
            System.out.println(add);
        }
        return listOfStrings;
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
        String myText = new testseparate().getFile();
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList= new testseparate().separateLine(myText);
        System.out.println(arrayList);
    }
}
