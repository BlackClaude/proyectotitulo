package com.example.claud.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class Sim extends AppCompatActivity {

    private LineChart mLChart;
    private LineData data;
    private ArrayList<Entry> yVals1;


    public int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sim);

        mLChart = findViewById(R.id.Lchart1);
        Bundle params = this.getIntent().getExtras();
        if(params != null){
            int data = Integer.parseInt(params.getString("data"));
            int time = Integer.parseInt(params.getString("time"));
            setData(data, time);
        }else{
            //explota
        }
    }



    public void verNoticias(View v){
        Intent intent = new Intent(this, Noticias.class);
        intent.putExtra("ultimaWea", yVals1.get(yVals1.size() - 1).getY());
        startActivity(intent);
    }

    @Override
    public void onResume(){
        super.onResume();

    }

    public  void setData(int cap, int time){
        yVals1 = new ArrayList<>();
        float bitcoin = cap;
        yVals1.add(new Entry(0, bitcoin));
        if(Difficulties.dif == 'B'){

            for (int i=1; i<=time; i++){
                if(i % 2 == 0 && i % 5 == 0){
                    bitcoin = (float) (bitcoin + bitcoin * 0.0015);
                    yVals1.add(new Entry(i, bitcoin));

                }else if(i % 2 == 0 && i % 3 == 0){
                    bitcoin = (float) (bitcoin - (bitcoin * 0.031416));
                    yVals1.add(new Entry(i, bitcoin));
                } else if (i % 7 == 0) {
                    bitcoin = (float) (bitcoin + (bitcoin * 0.18));
                    yVals1.add(new Entry(i, bitcoin));
                } else if (i % 3 == 0){
                    bitcoin = (float) (bitcoin + (bitcoin * 0.75));
                    yVals1.add(new Entry(i, bitcoin));
                } else if (i % 5 == 0){
                    bitcoin = (float) (bitcoin + bitcoin/2.19);
                    yVals1.add(new Entry(i, bitcoin));
                } else if (i % 2 == 0){
                    bitcoin = (float) (bitcoin - bitcoin /2.115);
                    yVals1.add(new Entry(i, bitcoin));
                } else if (i % 1 == 0){
                    bitcoin = (float) (bitcoin +  bitcoin * 0.11 );
                    yVals1.add(new Entry(i, bitcoin));
                }
            }
        }else if (Difficulties.dif == 'A'){
            for (int i=1; i<=time; i++){
                if(i % 2 == 0 && i % 5 == 0){
                    bitcoin = (float) (bitcoin *(Math.random()*(2 - 0.5) + 0.5));
                    yVals1.add(new Entry(i, bitcoin));
                }else if(i % 2 == 0 && i % 3 == 0){
                    bitcoin = (float) (bitcoin *(Math.random()*(2 - 0.3) + 0.1));
                    yVals1.add(new Entry(i, bitcoin));
                } else if (i % 7 == 0) {
                    bitcoin = (float) (bitcoin *(Math.random()*(4.2 - 0.18) + 0.18));
                    yVals1.add(new Entry(i, bitcoin));
                } else if (i % 3 == 0){
                    bitcoin = (float) (bitcoin *(Math.random()*(2.5 - 0.75) + 0.75));
                    yVals1.add(new Entry(i, bitcoin));
                } else if (i % 5 == 0){
                    bitcoin = (float) (bitcoin *(Math.random()*(2.19 - 0.19) + 0.19));
                    yVals1.add(new Entry(i, bitcoin));
                } else if (i % 2 == 0){
                    bitcoin = (float) (bitcoin *(Math.random()*(2 - 0.01) + 0.01));
                    yVals1.add(new Entry(i, bitcoin));
                } else if (i % 1 == 0){
                    bitcoin = (float) (bitcoin *(Math.random()*(1.76 - 0.11) + 0.11));
                    yVals1.add(new Entry(i, bitcoin));
                }
            }

        }

        LineDataSet set1;

        set1 = new LineDataSet(yVals1, "Bitcoin");
        set1.setColor(Color.RED);
        set1.setDrawCircles(true);
        set1.setLineWidth(7f);

        data = new LineData(set1);
        mLChart.setData(data);
    }

}
