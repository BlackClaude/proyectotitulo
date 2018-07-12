package com.example.claud.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.claud.myapplication.model.ApplicationGlobals;
import com.example.claud.myapplication.thread.ActionListener;
import com.example.claud.myapplication.thread.RecurrentThread;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;


public class Sim extends AppCompatActivity implements ActionListener{
    private final static long MS_THREAD = 1000;
    private LineChart mLChart;
    private ArrayList<Float> varCap = new ArrayList<>();
    private ArrayList<Integer> varTim = new ArrayList<>();
    private LineData data;
    private ArrayList<Entry> yVals1;
    private RecurrentThread thread;
    private float dineroRestante, CryptC = 29, inicial = 0, nuevoValor= 0;
    private int cantidadCrypt = 0;
    private int cont= 0;
    private int tiempo = 0;
    private int cont2= 0;
    private boolean flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sim);
        thread = new RecurrentThread(MS_THREAD);
        thread.addListener(this);
        thread.start(); // inicia el hilo
        yVals1 = new ArrayList<>();

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
        final TextView dia = findViewById(R.id.dia);


        valorC.setText("Valor ฿ :" + nuevoValor);
        wallet.setText("Saldo: $" + dineroRestante);
        cantC.setText("฿ :" + cantidadCrypt);


    }


    //INTENT DE ACTIVIDAD PARA VER NOTICIAS
    public void verNoticias(View v){
        Intent intent = new Intent(this, Noticias.class);
        //intent.putExtra("varlorbc", flag);
        intent.putExtra("DiaActual", yVals1.get(yVals1.size() - 1).getY());
        startActivity(intent);
    }

    //FUNCIÓN PARA COMPRAR CRIPTOMONEDA (A MIGRAR A ACTIVIDAD PROPIA)
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
            flag = true;
            dineroRestante = dineroRestante - (CryptC*cantComprada);
            cantidadCrypt = cantidadCrypt + cantComprada;
            wallet.setText("Saldo: $" + dineroRestante);
            cantC.setText("฿ :" + cantidadCrypt);
        }
        else{
            Toast.makeText(this, "No tienes saldo suficiente para realizar esta compra.", Toast.LENGTH_SHORT).show();
        }
    }

    //FUNCIÓN PARA VENDER CRIPTOMONEDA (A MIGRAR A ACTIVIDAD PROPIA)
    public void venderCripto(View v){
        //Intent intent = new Intent ()
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
            cantC.setText("฿ :" + cantidadCrypt);
        }
        else{
            Toast.makeText(this, "No posees esta cantidad de criptomonedas.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume(){
        super.onResume();

    }

    //FUNCIÓN PARA POBLAR GRÁFICO
    public  void setData(int i, int time){

        SharedPreferences.Editor spe = getPreferences(MODE_PRIVATE).edit();
        float cryptoCoin = CryptC;
        float ganancia = 0;

        if (cantidadCrypt == 0){
            ganancia = dineroRestante;
        } else if (dineroRestante == 0){
            ganancia = cantidadCrypt*nuevoValor;
        }
        else {
            ganancia = dineroRestante +(cantidadCrypt*nuevoValor);
        }
        if(i==0) {
            yVals1.add(new Entry(0, ganancia));
        }
        //for(int i=0;i<=cont;i++){

            Log.d("ComprobacionValores", "valor crypt= " + nuevoValor+" Día= "+ i + " Ganancia= " + ganancia+" arrayList= "+yVals1);
            if(i % 2 == 0 && i % 5 == 0){
                cryptoCoin = (float) (cryptoCoin + (cryptoCoin * 0.05)); //dar valores al "Y"
                yVals1.add(new Entry(i, ganancia));             //agregar valores x = x+1; y = math.random*range
                nuevoValor = cryptoCoin;
            }else if(i % 2 == 0 && i % 3 == 0){
                cryptoCoin = (float) (cryptoCoin - (cryptoCoin * 0.031416));
                yVals1.add(new Entry(i, ganancia));
                nuevoValor = cryptoCoin;
            } else if (i % 7 == 0) {
                cryptoCoin = (float) (cryptoCoin + (cryptoCoin * 0.18));
                yVals1.add(new Entry(i, ganancia));
                nuevoValor = cryptoCoin;
            } else if (i % 3 == 0){
                cryptoCoin = (float) (cryptoCoin + (cryptoCoin * 0.75));
                yVals1.add(new Entry(i, ganancia));
                nuevoValor = cryptoCoin;
            } else if (i % 5 == 0){
                cryptoCoin = (float) (cryptoCoin + cryptoCoin/2.19);
                yVals1.add(new Entry(i, ganancia));
                nuevoValor = cryptoCoin;
            } else if (i % 2 == 0){
                cryptoCoin = (float) (cryptoCoin - cryptoCoin /2.115);
                yVals1.add(new Entry(i, ganancia));
                nuevoValor = cryptoCoin;

            } else if (i % 1 == 0){
                cryptoCoin = (float) (cryptoCoin +  cryptoCoin * 0.11 );
                yVals1.add(new Entry(i, ganancia));
                nuevoValor = cryptoCoin;
            }
            varCap.add(cryptoCoin);
            varTim.add(time);

            spe.putInt("varCap", (int)cryptoCoin);
            spe.putInt("varTim", time);
            spe.commit();
        //}
        LineDataSet set1;

        set1 = new LineDataSet(yVals1, "Capital Total Día Anterior");
        set1.setColor(Color.BLUE);
        set1.setDrawCircles(true);
        set1.setLineWidth(7f);
       //set1.xAxis = mLChart.getXAxis();
        //xAxis.setGranularity(1f);
        data = new LineData(set1);
        mLChart.setData(data);

    }

    //FUNCIÓN PARA CAMBIAR AL DÍA SIGUIENTE Y EL VALOR DE LA CRIPTOMONEDA
   public void diaSiguiente(View v){
        final TextView valorC = findViewById(R.id.textView7);
        Intent intent = new Intent(this, FinSim.class);
        cont++;
        if (cont < tiempo) {
            setData(cont, tiempo);
            Log.d("ComprobacionValores", "cont " + cont + " tiempo " + tiempo);
            valorC.setText("Valor ฿ :" + Math.round(nuevoValor));
            notificationcall();
            Toast.makeText(this, "Se ha avanzado al Día "+cont+".", Toast.LENGTH_SHORT).show();
        }else{
            Bundle params = new Bundle();
            params.putFloat("inicial", inicial);
            params.putFloat("final", dineroRestante);
            intent.putExtras(params);
            startActivity(intent);
            thread.stopThread();
        }
    }


    //LECTOR DE ARCHIVO CSV

    /*private void readCryptData(){
        InputStream is = getResources().openRawResource(R.raw.data);
        BufferedReader reader = new BufferedReader(){
            new InputStreamReader (is, Charset.forName("UTF-8"))
        };

        String line;
        try{
            while ((line = reader.readLine() != null)){
                String()tokens = line.split("',");

            }
        }catch (IOException e){
            Log.wtf("Sim","No se pudo leer la línea " + line, e);
            e.printStackTrace();
        }
    }*/

    //FUNCIÓN DE PRUEBA CLASE DE VARIABLES GLOBALES
    public void setGlobal(){
        ApplicationGlobals ap = (ApplicationGlobals) this.getApplication();
        ap.setValorBitcoin(23);
    }


    //FUNCIÓN PARA INTERACTUAR CON THREAD, se itera cada 1s actualmente.
    @Override
    public void actionPerformed() {
        if(flag){
        cont2++;
        }
        if (cont2 == 5){
            notificationcall();
            cont2=0;
            flag=false;
        }
        Log.d("ComprobacionValores","cryptC = " + cont2);
    }
    public void notificationcall(){

        if(flag) {
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "Cambio_Valor");

            notificationBuilder.setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setWhen(System.currentTimeMillis())
                    .setTicker("CryptoMoneda")
                    .setSmallIcon(R.drawable.button)
                    .setPriority(Notification.PRIORITY_MAX) // this is deprecated in API 26 but you can still use for below 26. check below update for 26 API
                    .setContentTitle("Twitter #Bitcoin")
                    .setContentText("Algo ha pasado!")
                    .setContentInfo("Cryptomoneda");

            NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1, notificationBuilder.build());
        }

    }
}
