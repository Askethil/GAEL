package com.example.utilisateur.gael;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Utilisateur on 22/01/2017.
 */

public class articles extends AppCompatActivity {
    Button AjouterArticle;
    private ListView maListViewPerso;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article);
        AjouterArticle= (Button) findViewById(R.id.button2);
        AjouterArticle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(getApplicationContext(),articleAjout.class);
                startActivity(homeIntent);
            }
        });

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String maVar = extras.getString("idBout");
            invokeWS(maVar);

        }



    }
    public void invokeWS(final String id){

        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://gael.uk.to/restapi/web/app.php/articles",new AsyncHttpResponseHandler() {
            // When the response returned by REST has Http response code '200'
            @Override
            public void onSuccess(String response) {
                try {

                        // JSON Object
                        JSONArray jsonArray = new JSONArray(response);
                        //Récupération de la listview créée dans le fichier main.xml
                        maListViewPerso = (ListView) findViewById(R.id.listviewperso);

                        //Création de la ArrayList qui nous permettra de remplire la listView
                        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();

                        //On déclare la HashMap qui contiendra les informations pour un item
                        HashMap<String, String> map;
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject uneBoutique = jsonArray.getJSONObject(i);
                            if (id.equals(uneBoutique.getString("idboutique")) ) {

                            map = new HashMap<String, String>();
                            map.put("titre", uneBoutique.getString("lib"));
                            map.put("description", uneBoutique.getString("description"));
                            map.put("id", uneBoutique.getString("idarticle"));
                            listItem.add(map);

                        }
                        //Création d'un SimpleAdapter qui se chargera de mettre les items présent dans notre list (listItem) dans la vue affichageitem
                        SimpleAdapter mSchedule = new SimpleAdapter(getApplicationContext(), listItem, R.layout.articlelisteview,
                                new String[]{"titre", "description", "id"}, new int[]{R.id.textViewName, R.id.textViewdes, R.id.textViewid});

                        //On attribut à notre listView l'adapter que l'on vient de créer
                        maListViewPerso.setAdapter(mSchedule);

                        //Enfin on met un écouteur d'évènement sur notre listView
                        maListViewPerso.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            @SuppressWarnings("unchecked")
                            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                                //on récupère la HashMap contenant les infos de notre item (titre, description, img)
                                HashMap<String, String> map = (HashMap<String, String>) maListViewPerso.getItemAtPosition(position);
                                Intent i1 = new Intent(articles.this, articleDetail.class);
                                i1.putExtra("idArt", map.get("id"));
                                startActivityForResult(i1, 0);
                            }
                        });
                    }
                    // Else display error messag
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    Toast.makeText(getApplicationContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();

                }
            }
            // When the response returned by REST has Http response code other than '200'
            @Override
            public void onFailure(int statusCode, Throwable error,
                                  String content) {
                // Hide Progress Dialog
                // When Http response code is '404'
                if(statusCode == 404){
                    Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                }
                // When Http response code is '500'
                else if(statusCode == 500){
                    Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                }
                // When Http response code other than 404, 500
                else{
                    Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    }

