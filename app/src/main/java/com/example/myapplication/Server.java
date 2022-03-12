package com.example.myapplication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args){
        //For creating a new socket every time the client excepts the connection
        while (true) {
            try {
                ServerSocket serverSocket = new ServerSocket(53212); //waits for requests
                Socket socket = serverSocket.accept(); //accept the client socket --> WE CAN CONNECT NOW
                System.out.println("It worked out! Connected! Juhu :)");

                InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                //WRAP FOR MORE EFFICENCY
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                while (true) { //use to send data back and forward
                    String msgFromClient = bufferedReader.readLine();
                    System.out.println("Client send message: " + msgFromClient);
                    String antwort = "";
                    bufferedWriter.write(antwort);
                    bufferedWriter.newLine();
                    bufferedWriter.flush(); //otherwise bufferdwriter would wait till its full

                    if (msgFromClient.equals("0")) {
                        break;
                    }
                }

                socket.close();
                inputStreamReader.close();
                bufferedWriter.close();
                bufferedReader.close();
                outputStreamWriter.close();


            } catch (IOException exc) {
                System.out.println("It did not work! \nException message: " + exc.getMessage());
            }
        }


    }
}
