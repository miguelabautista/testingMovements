package com.ofertia.marsrover.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by root on 11/11/14.
 */
public class Server {

    private PrintWriter out;

    public void run() throws IOException {

        int portNumber = 9095;

        ServerSocket serverSocket = new ServerSocket(portNumber);
        Socket clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));

        try {
            String inputLine, outputLine;

            Responses res = new Responses();
            outputLine = res.processInput(null);
            out.println(outputLine);

            while ((inputLine = in.readLine()) != null) {
                outputLine = res.processInput(inputLine);
                if(outputLine != null && outputLine.equalsIgnoreCase("exit")){
                    out.println("GOODBYE!!!");
                    break;
                }else if(outputLine == null){
                    out.println("ERROR TRY AGAIN!!!!");
                }else{
                    out.println(outputLine);
                }
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        } finally {
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        }
    }
}
