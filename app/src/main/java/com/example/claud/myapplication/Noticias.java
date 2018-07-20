package com.example.claud.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.data.Entry;

import java.util.Random;

public class Noticias extends AppCompatActivity {
    static Sim sim;
    private int valorBajoAL = 1;
    private int valorAltoAL = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);
        sim = new Sim();
        Intent myIntent = getIntent();
        float ultimoValor = myIntent.getFloatExtra("DiaActual", 0F);


        final TextView textViewToChange = findViewById(R.id.textView4);

        Random r = new Random();
        int random = r.nextInt(valorAltoAL - valorBajoAL) + valorBajoAL;
        switch(random){
            case 1:
                textViewToChange.setText("Malas Noticias para la Criptomoneda La asociación de bancos nacional ha decidido de manera unánime betar el uso de la criptomoneda, norma que se hará vigente a partir de este mismo instante");
                break;
            case 2:
                textViewToChange.setText("La famosa empresa de videojuegos,Ztean, ha decidido a comenzar la criptomoneda como una alternativa de pago, y esto se hará vigente a contar de este momento.");
                break;
            case 3:
                textViewToChange.setText("El más nuevo y famoso ransomware por_que_ejecutas_exes_mandados_por_mail_por_un_desconocido ha afectado a una gran cantidad de computadores a nivel global. Lo curioso de este virus es que ofrece como único medio de pago criptomonedas, lo cual prevee de que incrementará drásticamente su precio! Buenas noticias para los inversores");
                break;
            /*case 4:
                textViewToChange.setText("BlockPay, la más nueva modalidad de pago de la cual se había hablado por meses, ha comenzado su marcha blanca en distintos comercios, siendo caracterizado por tener como única modalidad de pago la criptomoneda");
                break;
            case 5:
                textViewToChange.setText("La nueva empresa, E.R.R.O.R (Efemérides Raras Resuelven Objetar Raciocinio), deciden comenzar a ofrecer como alternativa de pago de su subscripción la criptomoneda.");
                break;
            case 6:
                textViewToChange.setText("El director del SII, Armando Mercado, ha comunicado de que un impuesto se le verá aplicado al corto plazo a la criptomoneda");
                break;
            case 7:
                textViewToChange.setText("Se ha encontrado una vulnerabilidad grave en el sistema de blockchain de las criptomonedas, esta vulnerabilidad permitiría alterar la información y que siga siendo válida, permitiendo robar criptomonedas.");
                break;
            case 8:
                textViewToChange.setText("Para fomentar el uso de las nuevas tecnologías, el gobierno entregará un bono a todas las personas que posean evidencia de que poseen al menos el equivalente a 50 mil pesos chilenos en criptomonedas");
                break;*/
        }

    }


    /*public void volverAtras(View v){
        Log.d("volverAtrasNoticias","Boton ejecutado con éxito");
        onBackPressed();
    }*/
}
