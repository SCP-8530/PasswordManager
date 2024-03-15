import com.google.gson.JsonObject;
import java.util.Scanner;

public class Main {

    Connexion connexion = new Connexion();

    public static void main(String[] args) {

        System.out.println("\n\nBienvenue sur votre manager de mot de passe :)\n");

        Menu();

    }

    /**
     * Fonction pour "nettoyer" la console.
     * */
    private static void CleanCMD() {
        for(int i = 0; i <= 50; i++) {
            System.out.println(" ");
        }
    }

    /**
     * Fonction pour Attendre avant de continuer
     * */
    private static void Wait() {
        System.out.println("Appuyer sur Enter pour continuer");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(input);
        CleanCMD();
    }

    /**
     * Fonction pour le menu
     * */
    private static void Menu() {
        while (true) {
            //Affichage du menu
            System.out.println("1. Afficher vos mots de passes");
            System.out.println("2. Creer un nouveau mot de passe");
            System.out.println("3. Modifier un mot de passe");
            System.out.println("4. Supprimer un mot de passe");
            System.out.println("5. Fermer l'application");

            while (true) {
                //Recuperer la commande
                Scanner scanner = new Scanner(System.in);
                System.out.printf("\nCommande : ");
                String input = scanner.nextLine();

                //Utilisation de la commande
                switch (input) {
                    case "1":
                        //Afficher la liste de mot de passe
                        CleanCMD();
                        AfficherPassword();

                        //Attente avant le retour au menu
                        Wait();
                        break;

                    default:
                        System.out.println("Commande non valide :(");
                }
            }
        }
    }

    /**
     * Afficher les mots de passes
     * */
    private static void AfficherPassword() {
        String jsonString = Connexion.Liste();


    }

}
