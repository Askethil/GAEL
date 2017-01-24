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

import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Utilisateur on 21/01/2017.
 */

public class articleAjout extends AppCompatActivity {
    EditText nom,description,stock,prix,boutique,image;
    Button BtnValider;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_ajout);
        nom = (EditText)findViewById(R.id.artNom);
        description = (EditText)findViewById(R.id.artDescription);
        stock = (EditText)findViewById(R.id.artStock);
        prix = (EditText)findViewById(R.id.artPrix);
        boutique = (EditText)findViewById(R.id.artIdBoutique);
        image = (EditText)findViewById(R.id.artUrlImage);
        BtnValider= (Button) findViewById(R.id.buttonArtAjout);
        BtnValider.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AjoutArticle();
            }
        });


    }
    public void AjoutArticle(){

        final String Lenom = nom.getText().toString();
        final String Ladescription = description.getText().toString();
        final String leStock = stock.getText().toString();
        final String Leprix = prix.getText().toString();
        final String Laboutique = boutique.getText().toString();
        final String Limage = image.getText().toString();

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
        String Theyear = sdf1.format(c.getTime());

        SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
        String Themonth = sdf2.format(c.getTime());

        SimpleDateFormat sdf3 = new SimpleDateFormat("dd");
        String Theday = sdf3.format(c.getTime());

        StringEntity entity;

        JSONObject dateObjet= new JSONObject();
        try {

            Date d=new Date();
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(new Date());

            dateObjet.put("year",Integer.toString(calendar.get(GregorianCalendar.YEAR)));

            dateObjet.put("month",Integer.toString(calendar.get(GregorianCalendar.MONTH)+1));
            dateObjet.put("day",Integer.toString(calendar.get(GregorianCalendar.DAY_OF_YEAR)));



        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject article= new JSONObject();
        try {
            article.put("lib", Lenom);

            article.put("datecreation", dateObjet);

            article.put("stock", leStock);
            article.put("description", Ladescription);
            article.put("idboutique",Laboutique);
            article.put("urlimage", Limage);
            article.put("prix", Leprix);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
             entity = new StringEntity(article.toString());
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

        client.post(context, "http://gael.uk.to/restapi/web/app.php/articles", params, "application/json",
                new AsyncHttpResponseHandler());
        Toast.makeText(getApplicationContext(), "article ajouter", Toast.LENGTH_LONG).show();
        Intent homeIntent = new Intent(getApplicationContext(),articleAjout.class);
        startActivity(homeIntent);
    }}
