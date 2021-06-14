import java.util.ArrayList;
import java.util.List;

public class LocalTab {



    private Local local = new Local();
    private List<Reservation> reservations = new ArrayList<>();

    public Local getLocal() {
        return local;
    }
    public class Reservation {
        private int id_reservation;
    }

    public  class  Local{


        private String ref;
        private String localisation;
        private double dimension;
        private int nbrePiece;
        private double tauxLoc;
        private double prix;
        private double cout;
        private String typeLocal;

        public String getRef() {
            return ref;
        }

        public String getLocalisation() {
            return localisation;
        }

        public double getDimension() {
            return dimension;
        }

        public int getNbrePiece() {
            return nbrePiece;
        }

        public double getTauxLoc() {
            return tauxLoc;
        }

        public double getPrix() {
            return prix;
        }

        public double getCout() {
            return cout;
        }

        public String getTypeLocal() {
            return typeLocal;
        }
        public void setRef(String ref) {
            this.ref = ref;
        }

        public void setLocalisation(String localisation) {
            this.localisation = localisation;
        }

        public void setDimension(double dimension) {
            this.dimension = dimension;
        }

        public void setNbrePiece(int nbrePiece) {
            this.nbrePiece = nbrePiece;
        }

        public void setTauxLoc(double taux) {
            this.tauxLoc = taux;
        }

        public void setPrix(double prix) {
            this.prix = prix;
        }

        public void setCout(double cout) {
            this.cout = cout;
        }
        public void setTypeLocal(String typeLocal) {
            this.typeLocal = typeLocal;
        }
    }

}
