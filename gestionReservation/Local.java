import java.util.ArrayList;
import java.util.List;

public abstract class Local {

    //debAttribut
    protected String ref;
    private static int num;
    private final int FORMAT = 4;

    private String localisation;
    protected double prix;
    protected double tauxLoc;



    private double cout;



    private String type;

    List<Reservation> reservations;
//finAttribur

    //deb_constructeur
    public Local(){
        this.ref = generateRef();

    }
    public Local(String localisation,String type,double tauxLoc,double prix,double cout){
        this.ref = generateRef();
        this.setLocalisation(localisation);
        this.setTauxLoc(tauxLoc);
        this.setPrix(prix);
        this.setCout(cout);
        this.reservations = new ArrayList<>();
    }
//fin_Constructeur

    //Generer des numero de 4 chiffre
    public String generateRef() {
        String nbref= String.valueOf(++num);
        String nombreZero="";
        for(int i=1; i<= FORMAT-nbref.length();i++){
            nombreZero += "0";
        }
        return "REF"+ nombreZero + num;
    }

    //getter__setter
    //ref
    public String getRef() {
        return ref;
    }
    public void setRef(String ref) {
        this.ref = ref;
    }
    //localisation
    public String getLocalisation() {
        return localisation;
    }
    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }
    //prix
    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
    //tauxLoc
    public double getTauxLoc() {
        return tauxLoc;
    }
    public void setTauxLoc(double tauxLoc) {
        this.tauxLoc = tauxLoc;
    }
    //type
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public double getCout() {
        return cout;
    }

    public void setCout(double cout) {
        this.cout = cout;
    }

//Fin getter_setter



}