public class Fuvar {
    private String taxi_id;
    private String indulas;
    private int idotartam;
    private double tavolsag;
    private double viteldij;
    private double borravalo;
    private double fizetes_modja;

    public Fuvar(String taxi_id, String indulas, int idotartam, double tavolsag, double viteldij, double borravalo, double fizetes_modja) {
        this.taxi_id = taxi_id;
        this.indulas = indulas;
        this.idotartam = idotartam;
        this.tavolsag = tavolsag;
        this.viteldij = viteldij;
        this.borravalo = borravalo;
        this.fizetes_modja = fizetes_modja;
    }
    public Fuvar(String line){
        String[] data= line.split(";");
        this.taxi_id = data[0];
        this.indulas = data[1];
        this.idotartam = Integer.parseInt(data[2]);
        this.tavolsag = Double.parseDouble(data[3]);
        this.viteldij = Double.parseDouble(data[4]);
        this.borravalo = Double.parseDouble(data[5]);
        this.fizetes_modja = Double.parseDouble(data[6]);
    }

    public String getTaxi_id() {
        return taxi_id;
    }

    public String getIndulas() {
        return indulas;
    }

    public int getIdotartam() {
        return idotartam;
    }

    public double getTavolsag() {
        return tavolsag;
    }

    public double getViteldij() {
        return viteldij;
    }

    public double getBorravalo() {
        return borravalo;
    }

    public double getFizetes_modja() {
        return fizetes_modja;
    }

    @Override
    public String toString() {
        return "Fuvar{" +
                "taxi_id='" + taxi_id + '\'' +
                ", indulas='" + indulas + '\'' +
                ", idotartam=" + idotartam +
                ", tavolsag=" + tavolsag +
                ", viteldij=" + viteldij +
                ", borravalo=" + borravalo +
                ", fizetes_modja=" + fizetes_modja +
                '}';
    }
}
