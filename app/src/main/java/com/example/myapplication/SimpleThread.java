package com.example.myapplication;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class SimpleThread extends Thread{
    public void run(){

        try{
            Socket clientSocket=new Socket("se2-isys.aau.at", 53212);
            BufferedReader inputStream=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            DataOutputStream outputStream=new DataOutputStream(clientSocket.getOutputStream());

        }
        catch (Exception exception){
            System.out.println("It did not work! \nException message: "+exception.getMessage());
        }
    }
}
