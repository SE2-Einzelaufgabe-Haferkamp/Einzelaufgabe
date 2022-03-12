package com.example.myapplication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception{

        try{
            Socket clientSocket = new Socket("se2-isys.aau.at", 53212);
            InputStreamReader inputStreamReader=new InputStreamReader(clientSocket.getInputStream()); // read data from the server
            //is a stream of bytes
            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(clientSocket.getOutputStream());  //output data to send msg to the server - output stream writer = bridge vom Byte Streams to Character Streams
            //is a stream of bytes

            //So we wrap those two, to increase efficency
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);//Wrap for more efficency - buffered reader
            BufferedWriter bufferedWriter=new BufferedWriter(outputStreamWriter); //Wrap for more efficency

            //Get the imput from keyboard
            Scanner sc=new Scanner(System.in);
            while (true){
                //SEND MSG TO SERVER
                String msgSend=sc.nextLine();
                bufferedWriter.write(msgSend);
                bufferedWriter.newLine(); //without new line it wouldnt know that the msg is over
                bufferedWriter.flush(); //bufferedWriter should send each msg. not wait until its full

                //WAIT FOR RESPONSE FROM SERVER
                System.out.println("Server response: "+bufferedReader.readLine());

                //END ENDLESS LOOP
                if(msgSend.equalsIgnoreCase("0")){
                    break;
                }
            }

            clientSocket.close();


        }catch(Exception exc){
            System.out.println("It did not work! Exception: "+exc.getMessage().toString());
        }

    }

}
