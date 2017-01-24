package com.example.utilisateur.gael;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Utilisateur on 22/01/2017.
 */

public class articleDetail extends AppCompatActivity {
    EditText lib,description;
    Button BtnValider;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajoutboutique);
        lib = (EditText)findViewById(R.id.editTextLibBout);
        description = (EditText)findViewById(R.id.description);
        BtnValider= (Button) findViewById(R.id.buttonValiderBoutique);
        BtnValider.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String maVar = extras.getString("idArt");
            Toast.makeText(getApplicationContext(), "ZZ"+maVar, Toast.LENGTH_LONG).show();

        }


    }

    public void invokeWS(StringEntity params,JSONObject b){

        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        Context context = this.getApplicationContext();

        client.post(context, "http://gael.uk.to/restapi/web/article", params, "application/json",
                new AsyncHttpResponseHandler());
        System.out.print(b);

    }
  }