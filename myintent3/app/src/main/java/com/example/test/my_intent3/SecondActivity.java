package com.example.test.my_intent3;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void buttonNext(View view) {
        Intent i = new Intent(this, ThirdActivity.class);
        startActivity(i);
    }

    public void buttonBefore(View view) {
        finish();
    }

    public void buttonOther(View view) {
    }
}
