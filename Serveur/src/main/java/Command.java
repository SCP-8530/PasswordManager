import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class Command implements Runnable {

    private ServerSocket serverSocket;
    private Socket clientSocket;

    public Command(ServerSocket serverSocket, Socket clientSocket) {
        this.serverSocket = serverSocket;
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
                    BufferedReader br = new BufferedReader(new FileReader("list_password.json"));
                    String jsonString = br.toString();
                    socketOut.println(jsonString);

                    //log
                    System.out.println("INFO: Liste envoyer avec succes");
                    break;
                } else {
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
