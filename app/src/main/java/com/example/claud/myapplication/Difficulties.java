package com.example.claud.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Difficulties extends AppCompatActivity {

    static char dif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.difficulties);
    }

    public void buttonGoBeginner(View v){
        dif = 'B';
        Intent intent = new Intent(this, Params.class);
        startActivity(intent);
    }

    public void buttonGoAdvanced(View v){
        dif = 'A';
        Intent intent = new Intent(this, Advanced.class);
        startActivity(intent);
    }
}
