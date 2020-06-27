package com.negocios.proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;

public class Main2Activity extends AppCompatActivity {
    EditText ListaItems;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private JSONArray listJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ListaItems = findViewById(R.id.ListaItems);

        this.preferences = getSharedPreferences("Immuni", MODE_PRIVATE);

        try {
            listJson = new JSONArray(this.preferences.getString("data", "[]"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i=0; i<listJson.length(); i++){
            try {
                ListaItems.append("Nombre: " + listJson.getJSONObject(i).getString("Nombre").toString()+ "\nEdad:  " + listJson.getJSONObject(i).getString("Edad").toString()+"\nGenero:"+listJson.getJSONObject(i).getString("Genero").toString()+"\n\t -----------\n");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public  void atras(View view){
        Intent miIntent = new Intent(Main2Activity.this, MainActivity.class);
        startActivity(miIntent);
    }
}
