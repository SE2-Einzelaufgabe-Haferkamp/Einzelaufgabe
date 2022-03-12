package com.example.myapplication;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void main(String[] args){

        try{
            Socket clientSocket = new Socket("se2-isys.aau.at", 53212);
            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream()); //we convert the raw data into stream
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in)); //get Import from User

            Integer ui= Integer.valueOf(inputStream.readLine()); //User import into Integer
            outputStream.writeInt(ui);// Write user import into Stream objekt
            //clientSocket.close();
        }catch(Exception exc){
            System.out.println("It did not work! \nException message: "+exc.getMessage());
        }

    }

}
