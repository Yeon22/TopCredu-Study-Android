package com.example.test.project_a;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void main(View view) {
        Intent i = new Intent(this, SecondActivity.class);
        i.putExtra("main",100);
        startActivity(i);
    }
}
