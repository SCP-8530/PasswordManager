import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

public class Connexion {
    public static Map<String, String> Liste() {
        try {
            //Connexion
            Socket socket = new Socket("localhost", 8080);

            //Recuperer les flux
            PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //Execution de la commande
            socketOut.println("LISTE");

            //Recuperer la liste et la transformer
            String str_map = socketIn.readLine();
            str_map = str_map.substring(1,str_map.length()-1);
            String[] data = str_map.split(",");
            Map<String, String> map = new HashMap<>();
            for (String keyValue: data) {
                String[] parts = keyValue.split("=",2);
                map.put(parts[0],parts[1]);
            }


            //fermeture de la connexion
            socket.close();

            //return
            return map;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void Send(Map<String,String> map) {
        try {
            //Connexion
            Socket socket = new Socket("localhost", 8080);

            //Recuperer les flux
            PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //Execution de la commande
            socketOut.println("SEND");

            //Recuperer la liste et la transformer
            socketOut.println(map);


            //fermeture de la connexion
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
