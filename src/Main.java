import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<Fuvar> fuvars = new ArrayList<>();
    public static void main(String[] args) {
        try{
            readFile("fuvar.csv");
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    private static void readFile(String fileName) throws IOException {
        BufferedReader bufferedReader= new BufferedReader(new FileReader(fileName));
        bufferedReader.readLine();
        String line = bufferedReader.readLine();
        while (line != null && !line.isEmpty()){
            fuvars.add(new Fuvar(line));
            line= bufferedReader.readLine();
        }
    }
}