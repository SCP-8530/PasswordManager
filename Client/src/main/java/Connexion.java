import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Connexion {
    public static String Liste() {
        try {
            //Connexion
            Socket socket = new Socket("localhost", 8080);

            //Recuperer les flux
            PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //Execution de la commande
            socketOut.println("LISTE");

            //Recuperer la liste
            String jsonString = socketIn.readLine();

            //fermeture de la connexion
            socket.close();

            //return
            return jsonString;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
