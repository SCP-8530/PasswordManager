import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    Connexion connexion = new Connexion();

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
    private static void AfficherPassword() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        String jsonString = Connexion.Liste();

        if (Objects.equals(jsonString, "{\"vide\":\"vide\"}")) {
            System.out.println("Vous n'avez aucun mot de passe sauvegarder");
        } else {
            Map<String,String> map_encripter = new Gson().fromJson( jsonString, new TypeToken<HashMap<String,String>>() {}.getType() );
            Map<String,String> map_decripter = Decryptage(map_encripter);

            int i = 0;
            for (Map.Entry<String, String> entry : map_decripter.entrySet()) {
                i++;
                System.out.printf("%s. %s: %s",i, entry.getKey(), entry.getValue());
            }

        }


    }

    private static Map<String, String> Decryptage(Map<String,String> map) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        //cle d'encryption
        SecretKey secretKey = CS_EncryptionUtils.getKeyFromPassword("Password","Salee");
        IvParameterSpec ivParameterSpec = CS_EncryptionUtils.generateIv(new byte[] { (byte)0xe0, 0x4f, (byte)0xd0,
                0x20, (byte)0xea, 0x3a, 0x69, 0x10, (byte)0xa2, (byte)0xd8, 0x08, 0x00, 0x2b,
                0x30, 0x30, (byte)0x9d });

        //boucle de decryptage
        for (Map.Entry<String,String> entry : map.entrySet()) {
            String mdp_crypter = entry.getValue();
            String mdp_decrypter = CS_EncryptionUtils.decrypt(mdp_crypter,secretKey,ivParameterSpec);
            entry.setValue(mdp_decrypter);
        }

        //envoie de la map
        return map;

    }

}
