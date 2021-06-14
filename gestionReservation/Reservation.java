
public class Reservation {
    private static int FORMAT = 4;
    private String id;
    private String date;
    private int duree;
    private boolean etat;
    private LocalTab local;
    private Client client;
    private int num;
    
//debConstructeur
    public Reservation(String date, int duree, boolean etat, LocalTab local, Client client) {
        this.id = generateid();
        this.setId(id);
        this.date = date;
        this.setDuree(duree);
        this.setEtat(etat);
        this.local = local;
        this.client = client;
    }

    public Reservation() {
        this.id = generateid();
    }

    public String generateid() {
        String nombreZero = "" ;
        String nombreDeRefString = String.valueOf(++num);
        for(int i = 1; i<= FORMAT - nombreDeRefString.length(); i++){
            nombreZero += "0";
        }
        return nombreZero + nombreDeRefString;
    }


//finConstructeur

//getterSetterdeb
//id
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
//date
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
//duree
    public int getDuree() {
        return duree;
    }
    public void setDuree(int duree) {
        this.duree = duree;
    }
//etat
    public boolean getEtat() {
        return etat;
    }
    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public LocalTab getLocal() {
        return local;
    }

    public Client getClient() {
        return client;
    }

    public void setLocal(LocalTab local) {
        this.local = local;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
}
