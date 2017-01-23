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
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Utilisateur on 22/01/2017.
 */

public class ajoutBoutique extends AppCompatActivity {
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
                AjoutBoutique();
            }
        });
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String maVar = extras.getString("idBout");
            Toast.makeText(getApplicationContext(), "ZZ"+maVar, Toast.LENGTH_LONG).show();

        }


    }
    public void AjoutBoutique(){

        final String Lelib = lib.getText().toString();
        final String LaDescription = description.getText().toString();

        StringEntity entity;

        JSONObject boutique= new JSONObject();
        JSONObject dates= new JSONObject();
        try {
            Date d=new Date();
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(new Date());

            dates.put("year",Integer.toString(calendar.get(GregorianCalendar.YEAR)));

            dates.put("month",Integer.toString(calendar.get(GregorianCalendar.MONTH)+1));
            dates.put("day",Integer.toString(calendar.get(GregorianCalendar.DAY_OF_YEAR)));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            boutique.put("lib",Lelib);
            boutique.put("datecreation", dates);
            boutique.put("description",LaDescription);
            boutique.put("urlimage", "dd");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            entity = new StringEntity(boutique.toString());
            invokeWS(entity,boutique);
        }catch(IOException ex) {
            //Do something witht the exception
        }
        // Invoke RESTful Web Service with Http parameters

    }
    public void invokeWS(StringEntity params,JSONObject b){

        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        Context context = this.getApplicationContext();

        client.post(context, "http://gael.uk.to/restapi/web/boutique", params, "application/json",
                new AsyncHttpResponseHandler());
        System.out.print(b);

    }
  }