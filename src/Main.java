import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
            System.out.printf("7.a Hibás sorok száma: %s%n", hibasSorokSzama());
            System.out.printf("7.b Hibás sorok összes időtartama: %s%n", hibasSorokIdotartam());
            System.out.printf("7.c Hibás sorok teljes bevétele: %s%n",hibasSorokBevetel());
            System.out.printf("8. Szerepel-e a 1452-es taxis: %s%n", szerepelTaxis()?"Igen":"Nem");
            System.out.printf("9. Három legrövidebb fuvar: %s%n", legRovidebbFuvar());
            System.out.printf("10. December 24-i fuvarok száma: %s%n", decemberFuvarok());
            System.out.printf("11. December 31-i borravalók aránya: %s %%%n", borravaloArany());
        } catch (IOException e) {
            System.err.println(e);

        }
    }
    private static double borravaloArany(){
        return (double) fuvars.stream().filter(fuvar -> fuvar.getIndulas().contains("12-31")).toList().stream()
                .filter(fuvar -> fuvar.getBorravalo()>0).count() / fuvars.stream().filter(fuvar -> fuvar.getIndulas()
                .contains("12-31")).toList().size()*100;
    }
    private static long decemberFuvarok(){
        return fuvars.stream().filter(fuvar -> fuvar.getIndulas().contains("12-24")).count();
    }
    private static List<Fuvar> legRovidebbFuvar(){
        return fuvars.stream().filter(fuvar -> fuvar.getIdotartam()>0).sorted(Comparator.comparingInt(Fuvar::getIdotartam))
                .limit(3).toList();
    }
    private static boolean szerepelTaxis(){
        return fuvars.stream().anyMatch(fuvar->fuvar.getTaxi_id()==1452);
    }
    private static double hibasSorokBevetel(){
        return fuvars.stream().filter(fuvar -> fuvar.getIdotartam()>0 && fuvar.getViteldij()>0 && fuvar.getTavolsag()==0)
                .collect(Collectors.toList()).stream().mapToDouble(fuvar-> fuvar.getViteldij()+ fuvar.getBorravalo()).sum();
    }
    private static int hibasSorokIdotartam(){
        return fuvars.stream().filter(fuvar -> fuvar.getIdotartam()>0 && fuvar.getViteldij()>0 && fuvar.getTavolsag()==0)
                .collect(Collectors.toList()).stream().mapToInt(Fuvar::getIdotartam).sum();
    }
    private static long hibasSorokSzama(){
        return fuvars.stream().filter(fuvar -> fuvar.getIdotartam()>0 && fuvar.getViteldij()>0 && fuvar.getTavolsag()==0)
                .collect(Collectors.toList()).size();
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