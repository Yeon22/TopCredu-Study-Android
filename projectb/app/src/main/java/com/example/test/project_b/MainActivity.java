package com.example.test.project_b;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void nextFruit(View view) {
        Intent i = new Intent(this, FruitActivity.class);
        startActivity(i);
    }

    public void nextItem(View view) {
        Intent i = new Intent(this, ItemActivity.class);
        startActivity(i);
    }

    public void nextJob(View view) {
        Intent i = new Intent(this, PersonActivity.class);
        startActivity(i);
    }
}
