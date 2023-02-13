package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
  public static void main(String[] args) {
    System.out.println("Let's rock!");
    int port = 8099;
    String nameAnswer = "";
    String ageAnswer = "";

    try (ServerSocket serverSocket = new ServerSocket(port)) {
      while (true) {
        try(
          Socket clientSocket = serverSocket.accept();
          BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {

          if (nameAnswer.isEmpty() && ageAnswer.isEmpty()) {
            nameAnswer = in.readLine();
          } else if (!nameAnswer.isEmpty() && ageAnswer.isEmpty()) {
            ageAnswer = in.readLine();
          } else {
            final String welcomeMessage = ageAnswer.equals("no")
              ? "Welcome to the adult zone, %s! Have a good rest, or a good working day!"
              : "Welcome to the kids area, %s! Let's play!";
            System.out.printf(welcomeMessage, nameAnswer);
            break;
          }
        }
      }
    } catch (Exception e) {
      System.out.println("Server side error "+e.getMessage());
    }
  }
}
