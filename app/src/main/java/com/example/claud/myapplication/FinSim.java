package com.example.claud.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class FinSim extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compra);
        Bundle params = this.getIntent().getExtras();
        float inicial = params.getFloat("inicial");
        float cantFinal = params.getFloat("final");
        float puntaje = ((cantFinal*100)/inicial)-100;


        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("Puntaje");
        dialogo1.setMessage("Tu rendimiento fue de un " + puntaje +"%");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                aceptar();
            }
        });

        dialogo1.show();
    }

    public void aceptar() {
        Toast t=Toast.makeText(this," Gracias por probar la simulación!", Toast.LENGTH_SHORT);
        t.show();
    }
}