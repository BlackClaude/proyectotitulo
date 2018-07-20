package com.example.claud.myapplication.util;

import android.util.Log;

import com.example.claud.myapplication.R;
import com.example.claud.myapplication.model.ValorBitcoin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {


    public static List<ValorBitcoin> getValorBitconByInputStream(InputStream is){
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        String line = null;
        List<ValorBitcoin> lista = new ArrayList<>();
        try{
            while ((line = reader.readLine()) != null){
                String[] split = line.split(",");
                long timestamp = Long.parseLong(split[0]);
                double valorbitcoin = Double.parseDouble(split[1]);
                double cantidad = Double.parseDouble(split[2]);
                ValorBitcoin valor = new ValorBitcoin(timestamp,valorbitcoin,cantidad);
                lista.add(valor);
            }
            return lista;
        }catch (IOException e){
            Log.wtf("Sim","No se pudo leer la línea " + line, e);
            e.printStackTrace();
        }
        return null;
    }
}
