import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.awt.desktop.SystemSleepEvent;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {

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
    private static void Menu() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        //menu de l'app
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

                boolean commande_valide = true;

                //Afficher la liste de mot de passe
                if (input.equals("1")) {
                    AfficherPassword();
                    break;
                }
                else if (input.equals("2")) {
                    Creer_newPassword();
                    break;
                }
                else {
                    System.out.println("Commande non valide :(");
                }

            }

            //fin de la commande
            Wait();
            CleanCMD();
        }
    }

    /**
     * Afficher les mots de passes
     * */
    private static void AfficherPassword() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        Map<String,String> map_encripter = Connexion.Liste();

        if (map_encripter.containsKey("vide")) { //aucun mot de passe dans la base de donne
            System.out.println("Vous n'avez aucun mot de passe sauvegarder");
        } else { //mot de passe present et decryptage du mot de passe
            Map<String,String> map_decripter = Criptage.Decryptage(map_encripter);

            int i = 0;
            for (Map.Entry<String, String> entry : map_decripter.entrySet()) {
                i++;
                System.out.printf("%s. %s: %s",i, entry.getKey(), entry.getValue());
            }
        }


    }

    private static void Creer_newPassword() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeySpecException, InvalidKeyException {
        //Connaitre le site lieu au mot de passe
        Scanner scanner = new Scanner(System.in);
        System.out.printf("\nPour qu'elle site est destiner le mot de passe? ");
        String siteWeb = scanner.nextLine();

        //connaitre le type de mot de passe desirer
        System.out.println("Selectionner le type de mot de passe desirer");
        System.out.println("1. Mot de passe personnel");
        System.out.println("2. Mot de passe generer numerique");
        System.out.println("3. Mot de passe generer complexe");

        //boucle de commande
        String password;
        while (true) {
            String command = scanner.nextLine();

            //password custom
            if (command.equals("1")) {
                System.out.printf("Veuillez entre votre mot de passe : ");
                password = scanner.nextLine();

                //fin de la boucle de commande
                break;
            }

            //password numerique
            else if (command.equals("2")) {
                System.out.printf("Combien de chiffre necessaire : ");
                while (true) {
                    String input = scanner.nextLine();
                    if (input.matches("^\\d*$")) {
                        PasswordNumerique passwordNumerique = new PasswordNumerique(Integer.parseInt(input));
                        password = passwordNumerique.genererPassword();
                        break;
                    } else {
                        System.out.printf("Veuillez mettre un chiffre s'il vous plait : ");
                    }
                }

                //fin de la boucle de commande
                break;
            }

            //password complex
            else if (command.equals("3")) {
                System.out.printf("Combien de caractere necessaire : ");
                while (true) {
                    String input = scanner.nextLine();
                    if (input.matches("^\\d*$")) {
                        PasswordComplexe passwordComplexe = new PasswordComplexe(Integer.parseInt(input));
                        password = passwordComplexe.genererPassword();
                        break;
                    } else {
                        System.out.printf("Veuillez mettre un chiffre s'il vous plait : ");
                    }
                }

                //fin de la boucle de commande
                break;
            }

            //erreur de commande
            else {
                System.out.println("La commande est non valide.");
                System.out.printf("Veuillez reessayer : ");
            }
        }

        //sauvegarde du mot de passe
        //recuperation des mots de passe
        Map<String,String> map_encripter = Connexion.Liste();

        //aucun mot de passe dans la base de donne
        if (map_encripter.containsKey("vide")) {
            //Ajoute mot de passe dans la base de donne
            Map<String, String> map = new HashMap<>();
            map.put(siteWeb,password);

            //encrypter la base de donne et sauvegarder sur le serveur
            map = Criptage.Encryptage(map);
            Connexion.Send(map);
            System.out.println("Mot de passe enregistrer avec succes.");
        }

        //mot de passe present et decryptage du mot de passe
        else {
            Map<String,String> map_decripter = Criptage.Decryptage(map_encripter);

            int i = 0;
            for (Map.Entry<String, String> entry : map_decripter.entrySet()) {
                i++;
                System.out.printf("%s. %s: %s",i, entry.getKey(), entry.getValue());
            }
        }
    }
}
