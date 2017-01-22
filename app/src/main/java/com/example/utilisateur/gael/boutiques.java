package com.example.utilisateur.gael;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Utilisateur on 22/01/2017.
 */

public class boutiques extends AppCompatActivity {
    Button AjouterBoutique;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boutiques);
        AjouterBoutique= (Button) findViewById(R.id.button2);
        AjouterBoutique.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(getApplicationContext(),ajoutBoutique.class);
                startActivity(homeIntent);
            }
        });


    }
}
