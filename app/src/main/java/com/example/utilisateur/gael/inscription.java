package com.example.utilisateur.gael;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
/**
 * Created by Utilisateur on 21/01/2017.
 */

public class inscription extends AppCompatActivity {
    EditText nom,prenom,email,login,mdp;
    Button BtnValider;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription);
        nom = (EditText)findViewById(R.id.editTextNom);
        prenom = (EditText)findViewById(R.id.editTextPrenom);
        email = (EditText)findViewById(R.id.editTextEmail);
        login = (EditText)findViewById(R.id.editTextLogin);
        mdp = (EditText)findViewById(R.id.editTextMDP);
        BtnValider= (Button) findViewById(R.id.buttonValider);
        BtnValider.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                InscriptionUtilisateur();
            }
        });


    }
    public void InscriptionUtilisateur(){

        final String Lenom = nom.getText().toString();
        final String Leprenom = prenom.getText().toString();
        final String leemail = email.getText().toString();
        final String Lelogin = login.getText().toString();
        final String Lemdp = mdp.getText().toString();

        StringEntity entity;

        JSONObject utilisateur= new JSONObject();
        try {
            utilisateur.put("nom", Lenom);
            utilisateur.put("prenom", Leprenom);
            utilisateur.put("login", Lelogin);
            utilisateur.put("mdp", Lemdp);
            utilisateur.put("idboutique", 1);
            utilisateur.put("email", leemail);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
             entity = new StringEntity(utilisateur.toString());
            invokeWS(entity);
        }catch(IOException ex) {
            //Do something witht the exception
        }
        // Invoke RESTful Web Service with Http parameters

    }

    public void invokeWS(StringEntity params){

        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        Context context = this.getApplicationContext();

        client.post(context, "http://gael.uk.to/restapi/web/utilisateur", params, "application/json",
                new AsyncHttpResponseHandler());
        Toast.makeText(getApplicationContext(), "Inscription valide", Toast.LENGTH_LONG).show();
        Intent homeIntent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(homeIntent);
    }}
