package com.example.claud.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.claud.myapplication.model.ApplicationGlobals;

public class Venta extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.venta);
    }
    
    public void asd(){
        
        ApplicationGlobals ap = (ApplicationGlobals) this.getApplication();
        float valor = ap.getValorBitcoin();

    }
    public void realizarVenta(View v){
        Log.d("Venta","Boton ejecutado con éxito");
        onBackPressed();
    }
}
