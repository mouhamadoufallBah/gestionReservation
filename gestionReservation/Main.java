import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String args[]){
        Service service = new Service();
        JSONArray listLocal = new JSONArray();
        JSONArray listJsonClient = new JSONArray();
        JSONArray listJsonReservation = new JSONArray();

        int choix;
        do
        {
            choix = menu();

            switch(choix)
            {
                case 1:

                    //Recuperation des données au clavier : user
                    System.out.println("Entrer le localisation : ");
                    String loc = scanner.nextLine();
                    //type
                    String typeLocal ;
                    int typeLocalInt ;
                    do{
                        System.out.println("type de local : "
                                + "\n 1-Chambre"
                                + "\n 2-Appartement");
                        typeLocal = scanner.nextLine();
                        typeLocalInt = Integer.parseInt(typeLocal);
                    }while (typeLocalInt != 2 && typeLocalInt !=1 ) ;


                    Local local ;
                    double dimension = 0;
                    int nbrePiece = 0;
                    if(typeLocalInt == 1) {
                        System.out.println("Entrer la dimension : ");
                        dimension= scanner.nextDouble();
                        local = new Chambre(dimension);
                    }else {
                        System.out.println("Entrer le nombre de piece : ");
                        nbrePiece = scanner.nextInt();
                        local = new Appartement(nbrePiece);
                    }

                    //taux
                    System.out.println("Entrer le taux : ");
                    double taux = scanner.nextDouble();
                    //prix
                    System.out.println("Entrer le prix : ");
                    int prix = scanner.nextInt();
                    double cout = prix*taux;
                    System.out.println("Local ajouter avec succées!!!");
                    Local c;
                    if(typeLocalInt == 1)
                        c= new Chambre(loc,typeLocal,dimension,taux,prix,cout);
                    else c = new Appartement(loc,typeLocal,taux,prix,nbrePiece,cout);

                    JSONObject chambre = new JSONObject();

                    chambre.put("ref",c.getRef());
                    chambre.put("localisation",c.getLocalisation());
                    if(typeLocalInt == 1)
                        chambre.put("dimension", ((Chambre) c).getDimension());
                    else chambre.put("nbrePiece", ((Appartement) c).getNbrePiece());
                    chambre.put("tauxLoc", c.getTauxLoc());
                    chambre.put("prix", c.getPrix());
                    scanner.nextLine();

                    JSONObject locaux = new JSONObject();
                    locaux.put("local", chambre);

                    listLocal.add(locaux);
                    service.createJsonFIle(listLocal,service.LOCAL_FILE);

                    break;

                case 2:

                    service.listDeTousLesLocaux();



                    break;
                case 3:
                    System.out.println("Entrer votre nci");
                    int nci = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Entrer votre nom et prenom");
                    String nomComplet = scanner.nextLine();
                    System.out.println("Entrer votre telephone");
                    String tel = scanner.nextLine();
                    System.out.println("Veuillez entrer votre adresse");
                    String addresse = scanner.nextLine();
                    System.out.println("Veuillez entrer votre mail");
                    String email = scanner.nextLine();
                    //instanciation de client
                    Client client = new Client(nci,nomComplet, tel, addresse, email);
                    JSONObject infoClient = new JSONObject();
                    infoClient.put("nci",client.getNci());
                    infoClient.put("nomComplet",client.getNomComplet());
                    infoClient.put("tel",client.getTel());
                    infoClient.put("addresse",client.getAddresse());
                    infoClient.put("email",client.getEmail());
                    JSONObject clients= new JSONObject();
                    clients.put("client", infoClient);
                    listJsonClient.add(clients);

                    System.out.println("Voici nos locaux disponibles");
                    int nombreDelocauxDispo = service.listDesLocauxDisponibles();
                    if(nombreDelocauxDispo == 0) {
                        System.out.println("Désolé tous nos locaux sont réservés, repassez ultérieurement");
                        break;
                    }
                    System.out.println("Entrer le numero ref du local à réserver");
                    String ref =scanner.nextLine();
                    System.out.println("Voici les infos du local que vous avez choisi");
                    try {
                        LocalTab localTab = service.getLocal(ref, service.LOCAL_FILE);

                        service.afficherLocal(ref,service.LOCAL_FILE);
                        System.out.println("Veuillez entrer la date du resevation");
                        String date = scanner.nextLine();
                        System.out.println("Veuillez entrer la durée");
                        int duree = scanner.nextInt();
                        boolean etat = false;
                        Reservation reservation = new Reservation(date,duree,etat,localTab,client);

                        JSONObject infoReservation = new JSONObject();

                        infoReservation.put("id" , reservation.getId());
                        infoReservation.put("date" , reservation.getDate());
                        infoReservation.put("duree",reservation.getDuree());

                        System.out.println("Confirmer votre reservation (1 pour valider, 0 pour annuler)");
                        int validation = scanner.nextInt();
                        if(validation == 1 ) {
                            reservation.setEtat(true);
                            infoReservation.put("etat",reservation.getEtat());
                            infoReservation.put("ref_local",reservation.getLocal().getLocal().getRef());
                            infoReservation.put("nci",reservation.getClient().getNci());
                            service.createJsonFIle(listJsonClient, service.CLIENT_FILE);
                            listJsonReservation.add(infoReservation);
                            service.createJsonFIle(listJsonReservation, service.RESERVATION_FILE);
                            System.out.println("Réservation Valider pour la date "+reservation.getDate()+" !");
                            scanner.nextLine();
                        } else System.out.println("Réservation annuler ! ");

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    break;
                case 4:
                    System.out.println("Detail d'un local");
                    service.listDeTousLesLocaux();
                    System.out.println("entrer la ref du local");
                    String refe = scanner.nextLine();
                    System.out.println("Voici les infos du local que vous avez choisi");
                    service.voirDetailLocal(refe);
                    break;
                case 5:
                    System.out.println("Voici nos locaux reservés");
                    service.listDesLocauxReserver();
                    break;
                case 6:
                    System.out.println("Annulation de reservation");
                    System.out.println("Liste des locaux reservés");
                    service.listDesLocauxReserver();
                    System.out.println("Entrer le ref du local que vous souhaiteez annuler");
                    String refL = scanner.nextLine();
                    service.annulerReservation(refL);
                    System.out.println("Resrvation annulé!");


                    break;
                case 7:
                    System.out.println("Voici nos locaux disponible");
                    service.listDesLocauxDisponibles();
                    break;
                case 8:
                    break;
                default:
                    System.out.println("Mauvais choix :-( ");
            }

        } while (choix != 8);
    }

//affichage_menu

    public static int menu(){
        int choix;
        System.out.println
                ("Menu"
                        + "\n 1-Ajouter local"
                        + "\n 2-Lister local"
                        + "\n 3-Faire une reservation"
                        + "\n 4-Voir les détail d'un local"
                        + "\n 5-lister les locaux reservés"
                        + "\n 6-Annuler réservation"
                        + "\n 7-Lister les locaux disponible"
                        + "\n 8-Quitter "
                );
        System.out.print("Faites votre choix : ");
        choix = Integer.parseInt(scanner.nextLine());

        return choix;

    }
}
