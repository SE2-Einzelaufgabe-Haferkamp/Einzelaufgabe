package com.example.myapplication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Network extends Thread{
    String userInput;
    String serverAnswer;

    public Network(){userInput="0";}
    public Network(String input){userInput=input;}
    public String returnAnswer(){return this.serverAnswer;}

    @Override
    public void run(){

        try{
            Socket socket = new Socket("se2-isys.aau.at", 53212);
            BufferedReader serverAnswerStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //wrap into Burffered Reader for more efficency
            DataOutputStream serverInput=new DataOutputStream(socket.getOutputStream());
            //fill with user input

            serverInput.writeBytes( userInput+ "\n" );
            serverAnswer=serverAnswerStream.readLine();

            socket.close();

        }catch(Exception exc){
            System.out.println("It did not work! Exception: "+exc.getMessage().toString());
        }
    }

    public static void main(String[] args) {

    }

}
