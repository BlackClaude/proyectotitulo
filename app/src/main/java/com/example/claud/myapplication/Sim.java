package com.example.claud.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.claud.myapplication.thread.ActionListener;
import com.example.claud.myapplication.thread.RecurrentThread;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class Sim extends AppCompatActivity{

    private LineChart mLChart;

    ArrayList<Float> varCap = new ArrayList<>();
    ArrayList<Integer> varTim = new ArrayList<>();
    private LineData data;
    private ArrayList<Entry> yVals1;
    float dineroRestante;
    float CryptC = 29;
    int cantidadCrypt = 0;
    int cont= 0;
    int tiempo = 0;
    float inicial = 0;
    float nuevoValor= 0;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sim);
       /* Thread thread = new Thread (new RecurrentThread(1000));
        thread.start();*/

        mLChart = findViewById(R.id.Lchart1);
        Bundle params = this.getIntent().getExtras();
        if(params != null){
            int data = Integer.parseInt(params.getString("data"));
            int time = Integer.parseInt(params.getString("time"));
            dineroRestante = data; // valor que cambia a lo largo de la Simulación
            inicial = data; // valor inicial estático
            tiempo = time;
            setData(cont, time);
        }else{
            Log.d("FAILED DATA OBTENTION","ERROR AL OBTENER DATOS");
        }
        final TextView valorC = findViewById(R.id.textView7);
        final TextView wallet = findViewById(R.id.textView9);
        final TextView cantC = findViewById(R.id.textView);


        valorC.setText("Valor C! :" + nuevoValor);
        wallet.setText("Saldo: $" + dineroRestante);
        cantC.setText("C! :" + cantidadCrypt);


    }



    public void verNoticias(View v){
        Intent intent = new Intent(this, Noticias.class);
        //intent.putExtra("varlorbc", flag);
        intent.putExtra("ultimaWea", yVals1.get(yVals1.size() - 1).getY());
        startActivity(intent);
    }

    public void comprarCripto(View v) {
        EditText cantidadComprada = findViewById(R.id.editText4);
        int cantComprada = Integer.parseInt(cantidadComprada.getText().toString());
        final TextView wallet = findViewById(R.id.textView9);
        final TextView cantC = findViewById(R.id.textView);

        Log.d("ComprobacionValores","cryptC ");

        if (cantComprada == 0){
            Toast.makeText(this, "Por favor Ingresa una cantidad superior a 0.", Toast.LENGTH_SHORT).show();
        }
        else if (CryptC*cantComprada <= dineroRestante) {

            Toast.makeText(this, "Se ha comprado exitosamente " + cantComprada + " Cryptomonedas.", Toast.LENGTH_SHORT).show();
            dineroRestante = dineroRestante - (CryptC*cantComprada);
            cantidadCrypt = cantidadCrypt + cantComprada;
            wallet.setText("Saldo: $" + dineroRestante);
            cantC.setText("C! :" + cantidadCrypt);
        }
        else{
            Toast.makeText(this, "No tienes saldo suficiente para realizar esta compra.", Toast.LENGTH_SHORT).show();
        }
    }

    public void venderCripto(View v){
        EditText cantidadVendida = findViewById(R.id.editText5);
        int cantVendida = Integer.parseInt(cantidadVendida.getText().toString());
        final TextView wallet = findViewById(R.id.textView9);
        final TextView cantC = findViewById(R.id.textView);

        Log.d("ComprobacionValores","cryptC ");

        if (cantVendida == 0){
            Toast.makeText(this, "Por favor Ingresa una cantidad superior a 0.", Toast.LENGTH_SHORT).show();
        }
        else if (cantVendida <= cantidadCrypt) {

                Toast.makeText(this, "Se ha vendido exitosamente " + cantVendida + " Cryptomonedas.", Toast.LENGTH_SHORT).show();
            dineroRestante = dineroRestante + (nuevoValor*cantVendida);
            cantidadCrypt = cantidadCrypt - cantVendida;
            wallet.setText("Saldo: $" + dineroRestante);
            cantC.setText("C! :" + cantidadCrypt);
        }
        else{
            Toast.makeText(this, "No posees esta cantidad de criptomonedas.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume(){
        super.onResume();

    }

    /*@Override
    public void actionPerformed() {
    }
    cont++;
    Log.d("ComprobacionValores","ciclo numero  " + cont);*/


    public  void setData(int cont, int time){

        yVals1 = new ArrayList<>();
        SharedPreferences.Editor spe = getPreferences(MODE_PRIVATE).edit();
        float cryptoCoin = CryptC;
        yVals1.add(new Entry(0, cryptoCoin));
        for(int i=0;i<=cont;i++){
            if(i % 2 == 0 && i % 5 == 0){
                cryptoCoin = (float) (cryptoCoin + (cryptoCoin * 0.05)); //dar valores al "Y"
                yVals1.add(new Entry(i, cryptoCoin));             //agregar valores x = x+1; y = math.random*range
                nuevoValor = cryptoCoin;
            }else if(i % 2 == 0 && i % 3 == 0){
                cryptoCoin = (float) (cryptoCoin - (cryptoCoin * 0.031416));
                yVals1.add(new Entry(i, cryptoCoin));
                nuevoValor = cryptoCoin;
            } else if (i % 7 == 0) {
                cryptoCoin = (float) (cryptoCoin + (cryptoCoin * 0.18));
                yVals1.add(new Entry(i, cryptoCoin));
                nuevoValor = cryptoCoin;
            } else if (i % 3 == 0){
                cryptoCoin = (float) (cryptoCoin + (cryptoCoin * 0.75));
                yVals1.add(new Entry(i, cryptoCoin));
                nuevoValor = cryptoCoin;
            } else if (i % 5 == 0){
                cryptoCoin = (float) (cryptoCoin + cryptoCoin/2.19);
                yVals1.add(new Entry(i, cryptoCoin));
                nuevoValor = cryptoCoin;
            } else if (i % 2 == 0){
                cryptoCoin = (float) (cryptoCoin - cryptoCoin /2.115);
                yVals1.add(new Entry(i, cryptoCoin));
                nuevoValor = cryptoCoin;

            } else if (i % 1 == 0){
                cryptoCoin = (float) (cryptoCoin +  cryptoCoin * 0.11 );
                yVals1.add(new Entry(i, cryptoCoin));
                nuevoValor = cryptoCoin;
            }
            varCap.add(cryptoCoin);
            varTim.add(time);

            spe.putInt("varCap", (int)cryptoCoin);
            spe.putInt("varTim", time);
            spe.commit();
        }

        //Toast.makeText(this,"Se guardo Correctamente", Toast.LENGTH_SHORT).show();
        LineDataSet set1;

        set1 = new LineDataSet(yVals1, "valor C!");
        set1.setColor(Color.BLUE);
        set1.setDrawCircles(true);
        set1.setLineWidth(7f);

        data = new LineData(set1);
        mLChart.setData(data);
    }
    public void diaSiguiente(View v){
        final TextView valorC = findViewById(R.id.textView7);
        Intent intent = new Intent(this, Compra.class);
        cont++;
        if (cont < tiempo) {
            setData(cont, tiempo);
            Log.d("ComprobacionValores", "cont " + cont + " tiempo " + tiempo);
            valorC.setText("Valor C! :" + Math.round(nuevoValor));
        }else{
            Bundle params = new Bundle();
            params.putFloat("inicial", inicial);
            params.putFloat("final", dineroRestante);
            intent.putExtras(params);
            startActivity(intent);
        }
    }


}
