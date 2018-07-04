package com.example.claud.myapplication.model;

import android.app.Application;

public class ApplicationGlobals extends Application {

    private float valorBitcoin, saldo;
    int cantBitcoin = 0;

    public float getValorBitcoin() {
        return valorBitcoin;
    }

    public void setValorBitcoin(float valorBitcoin) {
        this.valorBitcoin = valorBitcoin;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float getCantBitcoin() {
        return cantBitcoin;
    }

    public void setCantBitcoin(int CantBitcoin) {
        this.cantBitcoin = cantBitcoin;
    }
}
