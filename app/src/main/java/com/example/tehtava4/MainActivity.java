package com.example.tehtava4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private ListView listView;
    private RequestQueue mQueue;
    private List<luokka> luokka;
    private adapteri Adapteri;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.button = findViewById(R.id.button);
        this.listView = findViewById(R.id.listView);
        mQueue = Volley.newRequestQueue(this);

        luokka = new ArrayList<luokka>();

        Adapteri = new adapteri(this, R.layout.layoutti, luokka);
        listView.setAdapter(Adapteri);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL = "https://jsonplaceholder.typicode.com/posts";
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        luokka luokka;
                        ArrayList<luokka> lista = new ArrayList<luokka>();

                        Type type = new TypeToken<ArrayList<luokka>>() {
                        }.getType();
                        Gson gson = new Gson();

                        lista = gson.fromJson(response.toString(), type);
                        Adapteri.addAll(lista);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.toString();
                    }
                });
                mQueue.add(jsonArrayRequest);

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Id on: " + Adapteri.getItemId((int)id), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
