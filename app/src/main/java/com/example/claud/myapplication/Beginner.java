package com.example.claud.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.SharedPreferences;
import android.widget.Toast;


public class Beginner extends AppCompatActivity {

    EditText capInit;
    EditText cantTime;
    Button clickButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beginner);

        capInit = findViewById(R.id.editText3);
        cantTime = findViewById(R.id.editText);
        clickButton = findViewById(R.id.button2);




    }



    public void buttonGoSim(View v){
        String capital = capInit.getText().toString();
        String tiempo = cantTime.getText().toString();
        Bundle params = new Bundle();
        params.putString("data", capital);
        params.putString("time", tiempo);

        Toast.makeText(this, "Se Inicia la Simulación", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Sim.class);
        intent.putExtras(params);
        startActivity(intent);
        }


}