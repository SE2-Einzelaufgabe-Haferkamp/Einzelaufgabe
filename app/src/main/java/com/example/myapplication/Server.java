package com.example.myapplication;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args){
        try{
            ServerSocket serverSocket = new ServerSocket(53212);
            Socket socket=serverSocket.accept(); //accept the client socket
            System.out.println("It worked out! Connected! Juhu :)");

            DataInputStream dataInputStream=new DataInputStream(socket.getInputStream()); //Data input stream
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));
            int matrikelnr=dataInputStream.readInt();//read Matrikelnummer from inputStream
            System.out.println("Client send message: "+matrikelnr);

            //socket.close();


        }catch(Exception exc){
            System.out.println("It did not work! \nException message: "+exc.getMessage());
        }


    }
}
