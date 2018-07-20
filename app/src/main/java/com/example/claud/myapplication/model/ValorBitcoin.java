package com.example.claud.myapplication.model;

public class ValorBitcoin {
    private long timestamp;
    private double valorBitcoin;
    private double cantidad;

    public ValorBitcoin(long timestamp, double valorBitcoin, double cantidad) {
        this.timestamp = timestamp;
        this.valorBitcoin = valorBitcoin;
        this.cantidad = cantidad;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getValorBitcoin() {
        return valorBitcoin;
    }

    public void setValorBitcoin(double valorBitcoin) {
        this.valorBitcoin = valorBitcoin;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "ValorBitcoin{" +
                "timestamp=" + timestamp +
                ", valorBitcoin=" + valorBitcoin +
                ", cantidad=" + cantidad +
                '}';
    }

    public String getTimeStampString(){
        return "time= " + timestamp;
    }

    public String getValorBitcoinString(){
        return "Valor Bitcoin: "+valorBitcoin;
    }
}
