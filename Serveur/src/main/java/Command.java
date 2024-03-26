import com.google.gson.Gson;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Command implements Runnable {

    private Socket clientSocket;

    public Command(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void Execution() {
        try {
            System.out.printf("DEBUG: Connexion du client: %s\n", clientSocket.getInetAddress().getHostAddress());

            //Avoir les flux d'entrer et de sortie
            PrintWriter socketOut = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader socketIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            //Recuperer les commandes du client
            while (true) {
                //log
                String command = socketIn.readLine();
                System.out.printf("DEBUG: Commande %s\n", command);

                if (Objects.equals(command, "LISTE")) {
                    //Envoie de la liste
                    Gson gson = new Gson();
                    BufferedReader br = new BufferedReader(new FileReader("list_password.json"));
                    Map map = gson.fromJson(br, Map.class);
                    socketOut.println(map);

                    //log
                    System.out.println("INFO: Liste envoyer avec succes");
                    break;
                }
                else if (Objects.equals(command, "SEND")) {
                    //Envoie de la liste
                    String map = socketIn.readLine();

                    //recreation de la map
                    map = map.substring(1,map.length()-1);
                    String[] data = map.split(",");
                    Map<String,String> newMap = new HashMap<>();
                    for (String keyValue : data) {
                        String[] parts = keyValue.split("=",2);
                        newMap.put(parts[0],parts[1]);
                    }

                    //enregistrement de la map
                    Gson gson = new Gson();
                    String json = gson.toJson(newMap, Map.class);
                    FileWriter writer = new FileWriter("list_password.json");
                    writer.write(json);
                    writer.close();

                    //log
                    System.out.println("INFO: Liste de mot de passe recu avec succes");
                    break;
                }
                else {
                    //log
                    System.out.println("INFO: La commande ne fonctionne pas");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        this.Execution();
    }

}
