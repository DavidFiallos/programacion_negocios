package com.negocios.proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText ptnombre;
    EditText ptedad;
    RadioGroup radioGroup;
    private SharedPreferences preference;
    private SharedPreferences.Editor editor;
    private JSONArray ImmuniD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ptnombre = findViewById(R.id.ptnombre);
        ptedad = findViewById(R.id.ptedad);
        radioGroup = (RadioGroup)findViewById(R.id.rdbGenero);

        preference = getSharedPreferences("Immuni", MODE_PRIVATE);
        editor = preference.edit();

        try {
            ImmuniD = new JSONArray(preference.getString("data", "[]"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void enviar(View view){
        String Nombre = ptnombre.getText().toString();
        String Edad = ptedad.getText().toString();
        String Genero = "";
        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        if (checkedRadioButtonId == -1)  {
        }
        else{ if (checkedRadioButtonId == R.id.radioButtonM) {
            Genero = "Masculino";
        }else{Genero = "femenino";} }

        JSONObject ImmuniItems = new JSONObject();
        try {
            ImmuniItems.put("Nombre", Nombre);
            ImmuniItems.put("Edad", Edad);
            ImmuniItems.put("Genero", Genero);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ImmuniD.put(ImmuniItems);

        editor.putString("data", ImmuniD.toString());
        editor.commit();

        ptnombre.setText("");
        ptedad.setText("");


        Intent miIntent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(miIntent);
    }
}
