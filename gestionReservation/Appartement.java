public class Appartement extends Local {
    //attribut
    private int nbrePiece;
//finAttribut

    //debConstructeur
    public Appartement(int nbrePiece) {
        this.setNbrePiece(nbrePiece);
    }
    public Appartement(String localisation,String typeLocal,double tauxLoc,double prix,int nbrePiece,double cout){
        super(localisation,typeLocal,tauxLoc, prix,cout);
        this.setNbrePiece(nbrePiece);
    }
//finconstructeiur

    //getter__setter
    public int getNbrePiece() {
        return nbrePiece;
    }

    public void setNbrePiece(int nbrePiece) {
        this.nbrePiece = nbrePiece;
    }
//fin_getter_setter


    //methode_cout
    public void cout(double montant){
        montant = prix*tauxLoc;
    }


}
