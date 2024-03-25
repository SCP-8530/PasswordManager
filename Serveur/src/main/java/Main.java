import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        serverSocket = new ServerSocket(8080);

        while (true){
            clientSocket = serverSocket.accept();

            Command command = new Command(serverSocket, clientSocket);
            Thread thread = new Thread(command);

            thread.start();
        }
    }
}
