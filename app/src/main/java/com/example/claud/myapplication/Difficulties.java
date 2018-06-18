package com.example.claud.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Difficulties extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.difficulties);
    }

    public void buttonGoBeginner(View v){
        Intent intent = new Intent(this, Beginner.class);
        startActivity(intent);
    }

    /*public void buttonGoTutorial(View v){
        Intent intent = new Intent(this, Tutorial.class);
        startActivity(intent);
    }


    public void buttonGoAdvanced(View v){
        Intent intent = new Intent(this, Advanced.class);
        startActivity(intent);
    }

    public void buttonGoExpert(View v){
        Intent intent = new Intent(this, Expert.class);
        startActivity(intent);
    }*/
}
