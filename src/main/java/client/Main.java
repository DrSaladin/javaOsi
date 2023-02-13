package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    String host = "netology.homework";
    int port = 8099;
    Scanner scanner = new Scanner(System.in);
    boolean isNameSelected = false;
    boolean isAgeSelected = false;
    String messageToSend;

    while (true) {
      try(
        Socket clientSocket = new Socket(host, port);
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
      ) {

        if (!isNameSelected && !isAgeSelected) {
          System.out.println("Write your name");
          messageToSend = scanner.nextLine();
          isNameSelected = true;
        } else if (isNameSelected && !isAgeSelected) {
          System.out.println("Are you child? (yes/no)");
          messageToSend = scanner.nextLine();
          isAgeSelected = true;
        } else {
          break;
        }

        out.println(messageToSend);

      } catch (Exception e) {
        System.out.println("Client side error "+e.getMessage());
      }
    }
  }
}
