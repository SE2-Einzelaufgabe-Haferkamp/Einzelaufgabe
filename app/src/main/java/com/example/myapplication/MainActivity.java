package com.example.myapplication;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    public static String alternierendeQuersumme(String mat){
        if(mat==null || mat.length()==0){
            return "Matrinkelnummer ungültig!";
        }
        if(mat.length()==8){
            int matrikelnr=parseInt(mat,10);
            int gerade=0;
            int ungerade=0;
            int [] array= new int[mat.length()];
            for(int i=0; i<array.length; i++){
                array[i]=matrikelnr%10;
                matrikelnr=matrikelnr/10;
            }
            for(int i=0; i<array.length; i++){
                if(i==0 || i%2==0){
                    gerade=gerade+array[i];
                }
                else{
                    ungerade=ungerade+array[i];
                }
            }
            int ergebnis = gerade - ungerade;
            if(ergebnis%2==0){
                return "Alternierende Quersumme = "+ergebnis+" (gerade)";
            }
            else{
                return "Alternierende Quersumme = "+ergebnis+" (ungerade)";
            }
        }
        return "Matrinkelnummer ungültig!";
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView rueckgabe=findViewById(R.id.serverAntwortText);
        Button send=findViewById(R.id.sendButton);
        Button calculate=findViewById(R.id.calculateButton);
        EditText mat=findViewById(R.id.inputUserMatrikelnr);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Network verbindung = new Network(mat.getText().toString());
                verbindung.start();
                try {
                    verbindung.join();
                }catch (Exception e){
                    e.getStackTrace();
                }
                rueckgabe.setText(verbindung.returnAnswer());
            }
        });


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rueckgabe.setText(alternierendeQuersumme(mat.getText().toString()));
            }
        });

    }

}