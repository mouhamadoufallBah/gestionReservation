public class Chambre extends Local{
    private double dimension;

    //constructeur
    public Chambre(double dimension){
        this.setDimension(dimension);

    }
    public Chambre(String localisation,String typeLocal,double dimension,double tauxLoc,double prix,double cout){
        super(localisation,typeLocal,tauxLoc,prix,cout);
        this.setDimension(dimension);

    }
    public Chambre(){

    }
//fin_Constructeur

    //getter__setter
    public double getDimension() {
        return dimension;
    }

    public void setDimension(double dimension) {
        this.dimension = dimension;
    }
//finGetter_setter

    //methode_Cout
    public void cout(Double montant){
        montant=prix*tauxLoc;
    }




}
