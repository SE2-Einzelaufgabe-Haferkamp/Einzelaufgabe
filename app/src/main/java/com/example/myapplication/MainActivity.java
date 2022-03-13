package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = findViewById(R.id.button);
        Button berechnen = findViewById(R.id.button2);
        TextView serverAnswer = findViewById(R.id.textView);
        EditText inputNumber = findViewById(R.id.editTextNumber);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serverAnswer.setVisibility(View.VISIBLE);
                String matrikelnummer = inputNumber.getText().toString();
                Connection con = new Connection(matrikelnummer);

                con.start();
                try {
                    con.join();
                } catch (InterruptedException e) {
                }

                serverAnswer.setText(con.getAnswer());
            }

        });
        berechnen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serverAnswer.setVisibility(View.VISIBLE);
                String matrikelnummer = inputNumber.getText().toString();
                Connection con = new Connection(matrikelnummer);

                con.start();
                try {
                    con.join();
                } catch (InterruptedException e) {
                }
                if(!con.getAnswer().equals("Dies ist keine gueltige Matrikelnummer")){
                    berechnen(matrikelnummer);
                }
                else{
                    serverAnswer.setText("Das ist keine Matrikelnummer");
                }
            }
        });
    }
    public void berechnen(String matNr) {
        int firstSum = 0;
        int secondSum = 0;
        int[] numbers = new int[matNr.length()];
        char[] c = matNr.toCharArray();

        for (int i = 0; i < matNr.length(); i++) {
            numbers[i] = (int) c[i]-48;
        }
        for (int i = 0; i < numbers.length; i++) {
            if (i % 2 == 0) {
                firstSum += numbers[i];
            } else {
                secondSum += numbers[i];
            }
        }
        int alternierendeQuersumme = firstSum-secondSum;
        if(alternierendeQuersumme%2==0){
            serverAnswer.setText("GERADE");
        }
        else{
            serverAnswer.setText("UNGERADE");
        }

    }

}