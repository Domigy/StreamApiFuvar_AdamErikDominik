import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    private static List<Fuvar> fuvars = new ArrayList<>();
    public static void main(String[] args) {
        try{
            readFile("fuvar.csv");
            System.out.printf("1. %d fuvar került az állományban%n", fuvars.size());
            System.out.printf("2. 6185-ös taxis bevétele: %s, fuvarok száma: %s%n", getSumTaxis(), getCoutTaxi());
            System.out.printf("3. Összesen megtett mérföldek: %s%n", getSumMerfold());
            System.out.printf("4. Leghosszabb fuvar: %s%n", getMaxTav());
            System.out.printf("5. Legbőkezűbb fuvar: %s%n", legbokezubbFuvar());
            System.out.printf("6. 4261-es taxis megtett km-ek: %s%n", getTaxisKm());
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    private static double getTaxisKm(){
        return fuvars.stream().filter(fuvar -> fuvar.getTaxi_id()== 4261).mapToDouble(Fuvar::getTavolsag).sum()*1.6;
    }
    private static Fuvar legbokezubbFuvar(){
        return fuvars.stream().max(Comparator.comparingDouble(fuvar -> fuvar.getBorravalo()/ fuvar.getViteldij())).orElse(null);
    }
    private static Fuvar getMaxTav(){
        return fuvars.stream().max(Comparator.comparingDouble(Fuvar::getTavolsag)).orElse(null);
    }
    private static double getSumMerfold(){
        return fuvars.stream().mapToDouble(Fuvar::getTavolsag).sum();
    }
    private static long getCoutTaxi(){
        return fuvars.stream().filter(fuvar -> fuvar.getTaxi_id()== 6185).count();
    }
    private static double getSumTaxis(){
        return fuvars.stream().filter(fuvar -> fuvar.getTaxi_id() == 6185)
                .mapToDouble(fuvar -> fuvar.getViteldij() + fuvar.getBorravalo())
                .sum();
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