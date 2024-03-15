import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {

        ServerSocket serverSocket;
        Socket clientSocket;

        try {
            //Ecoute des ports
            serverSocket = new ServerSocket(8080);
            clientSocket =  serverSocket.accept();
            System.out.printf("DEBUG: Connexion du client: %s\n", clientSocket.getInetAddress().getHostAddress());

            //Avoir les flux d'entrer et de sortie
            PrintWriter socketOut = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader socketIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            //Recuperer les commandes du client
            while (true) {
                //log
                String command = socketIn.readLine();
                System.out.printf("DEBUG: Commande %s\n", command);

                switch (command) {
                    case "LISTE":
                        //Envoie de la liste
                        Gson gson = new Gson();
                        BufferedReader br = new BufferedReader(new FileReader("list_password.json"));
                        String jsonString = gson.fromJson(br, String.class);
                        socketOut.printf(jsonString);

                        //log
                        System.out.println("INFO: Liste envoyer avec succes");
                        break;
                    default:
                        //log
                        System.out.println("INFO: La commande ne fonctionne pas");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
