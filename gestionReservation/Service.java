import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Service {
    public static final String LOCAL_FILE = "local.json";
    public static final String RESERVATION_FILE = "reservation.json";
    public static final String CLIENT_FILE = "client.json";

    public void createJsonFIle(JSONArray listJsonArray, String fileName) {
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(listJsonArray.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listDeTousLesLocaux() {
        try (FileReader reader = new FileReader(LOCAL_FILE)) {
            Gson gson = new Gson();
            LocalTab[] loc = gson.fromJson(reader, LocalTab[].class);

            for (int i = 0; i<loc.length;i++) {
                System.out.println("----------------");
                System.out.println("Local N°"+i);
                System.out.println("----------------");
                System.out.println("REF: "+ loc[i].getLocal().getRef());
                System.out.println("LOCALISATION: "+ loc[i].getLocal().getLocalisation());
                System.out.println("TAUX: "+ loc[i].getLocal().getTauxLoc());
                System.out.println("Prix: "+ loc[i].getLocal().getPrix());
                System.out.println("Prix: "+ loc[i].getLocal().getCout());
                if(loc[i].getLocal().getDimension() != 0d)
                    System.out.println("Dimension: "+ loc[i].getLocal().getDimension());
                if(loc[i].getLocal().getNbrePiece() != 0)
                    System.out.println("Nombres de pieces: "+ loc[i].getLocal().getNbrePiece());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  int listDesLocauxDisponibles() {
        int nombreDeLocauxDisponible = 0;
        try (FileReader reader = new FileReader(LOCAL_FILE)) {
            Gson gson = new Gson();
            LocalTab[] loc = gson.fromJson(reader, LocalTab[].class);
            for (int i = 0; i<loc.length;i++) {
                boolean etat_local = localReserver(loc[i].getLocal().getRef());
                if(etat_local == false) {
                    ++nombreDeLocauxDisponible;
                    System.out.println("----------------");
                    System.out.println("Local N°" + i);
                    System.out.println("----------------");
                    System.out.println("REF: " + loc[i].getLocal().getRef());
                    System.out.println("LOCALISATION: " + loc[i].getLocal().getLocalisation());
                    System.out.println("TAUX: " + loc[i].getLocal().getTauxLoc());
                    System.out.println("Prix: " + loc[i].getLocal().getPrix());
                    if (loc[i].getLocal().getDimension() != 0d)
                        System.out.println("Dimension: " + loc[i].getLocal().getDimension());
                    if (loc[i].getLocal().getNbrePiece() != 0)
                        System.out.println("Nombres de pieces: " + loc[i].getLocal().getNbrePiece());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nombreDeLocauxDisponible;
    }

    public  int listDesLocauxReserver() {
        int nombreDeLocauxReserver = 0;
        try (FileReader reader = new FileReader(LOCAL_FILE)) {
            Gson gson = new Gson();
            LocalTab[] loc = gson.fromJson(reader, LocalTab[].class);
            for (int i = 0; i<loc.length;i++) {
                boolean etat_local = localReserver(loc[i].getLocal().getRef());
                if(etat_local == true) {
                    ++nombreDeLocauxReserver;
                    System.out.println("----------------");
                    System.out.println("Local N°" + i);
                    System.out.println("----------------");
                    System.out.println("REF: " + loc[i].getLocal().getRef());
                    System.out.println("LOCALISATION: " + loc[i].getLocal().getLocalisation());
                    System.out.println("TAUX: " + loc[i].getLocal().getTauxLoc());
                    System.out.println("Prix: " + loc[i].getLocal().getPrix());
                    if (loc[i].getLocal().getDimension() != 0d)
                        System.out.println("Dimension: " + loc[i].getLocal().getDimension());
                    if (loc[i].getLocal().getNbrePiece() != 0)
                        System.out.println("Nombres de pieces: " + loc[i].getLocal().getNbrePiece());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nombreDeLocauxReserver;
    }

    public boolean localReserver(String ref) {
        try (FileReader reader = new FileReader(RESERVATION_FILE)) {
            Gson gson = new Gson();
            ReservationTab[] listReservation = gson.fromJson(reader, ReservationTab[].class);

            for (int i = 0; i<listReservation.length;i++) {
                if(listReservation[i].getRef_local().equals(ref))
                    return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public LocalTab getLocal(String ref, String file) throws FileNotFoundException {
        FileReader reader = new FileReader(file);
        Gson gson = new Gson();
        LocalTab[] loc = gson.fromJson(reader, LocalTab[].class);

        for (int i = 0; i<loc.length;i++) {
            if(loc[i].getLocal().getRef().equals(ref)) {
                return loc[i];
            }
        }
        return null;
    }

    public ReservationTab getReservation(String ref, String file) throws FileNotFoundException {
        FileReader reader = new FileReader(file);
        Gson gson = new Gson();
        ReservationTab[] res = gson.fromJson(reader, ReservationTab[].class);

        for (int i = 0; i<res.length;i++) {
            if(res[i].getRef_local().equals(ref)) {
                return res[i];
            }
        }
        return null;
    }

    public ClientTab getClient(int clientNci, String file) throws FileNotFoundException {
        FileReader reader = new FileReader(file);
        Gson gson = new Gson();
        ClientTab[] client = gson.fromJson(reader, ClientTab[].class);

        for (int i = 0; i < client.length; i++) {
            if (client[i].client.getNci() == clientNci) {
                return client[i];
            }
        }
        return null;
    }


        public void afficherLocal(String ref, String file) {
        LocalTab localTab = null;
        try {
            localTab = getLocal(ref, file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("REF: "+ localTab.getLocal().getRef());
        System.out.println("LOCALISATION: "+ localTab.getLocal().getLocalisation());
        System.out.println("TAUX: "+ localTab.getLocal().getTauxLoc());
        System.out.println("Prix: "+ localTab.getLocal().getPrix());
    }
    public void faireReservation(String ref) {
        JSONParser jsonL = new JSONParser();
        try (FileReader reader = new FileReader("local.json")) {
            Gson gson = new Gson();
            LocalTab[] loc = gson.fromJson(reader, LocalTab[].class);

            for (int i = 0; i<loc.length;i++) {
                if(loc[i].getLocal().getRef() == ref ){
                    System.out.println("Reservation à ete prise en charge");
                }
            }

            } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void annulerReservation(String ref) {
        boolean localEstReserve = localReserver(ref);
        if(localEstReserve == true) {
            try (FileReader reader = new FileReader(RESERVATION_FILE)) {
                Gson gson = new Gson();
                ReservationTab[] listReservation = gson.fromJson(reader, ReservationTab[].class);

                for (int i = 0; i < listReservation.length; i++) {
                    if (listReservation[i].getRef_local().equals(ref))
                        listReservation[i] = null;
                }
                gson.toJson(listReservation, new FileWriter(RESERVATION_FILE));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void afficherDetailLocalReserver(String ref) {
        LocalTab localTab = null;
        ReservationTab reservationTab = null;
        ClientTab clientTab = null;
        try {
            localTab = getLocal(ref, LOCAL_FILE);
            reservationTab = getReservation(ref,RESERVATION_FILE);
            clientTab = getClient(Integer.valueOf(reservationTab.getCni()),CLIENT_FILE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("REF: "+ localTab.getLocal().getRef());
        System.out.println("LOCALISATION: "+ localTab.getLocal().getLocalisation());
        System.out.println("TAUX: "+ localTab.getLocal().getTauxLoc());
        System.out.println("Prix: "+ localTab.getLocal().getPrix());
        System.out.println("Le Local est reservé par le client :");
        System.out.println("Nom : "+ clientTab.getClient().getNomComplet());
        System.out.println("Numero de carte d'identité : "+ clientTab.getClient().getNci());
        System.out.println("Voici les infos de la réservation:");
        System.out.println("Date : "+ reservationTab.getDate());
        System.out.println("Durée: "+ reservationTab.getDuree());
    }

    public void voirDetailLocal(String ref) {
        boolean localEstReserve = localReserver(ref);
        if(localEstReserve == false) {
            afficherLocal(ref,LOCAL_FILE);
        } else {
            afficherDetailLocalReserver(ref);
        }
    }
}
