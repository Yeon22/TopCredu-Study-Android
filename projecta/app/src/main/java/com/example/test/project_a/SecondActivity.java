package com.example.test.project_a;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void second(View view) {
        Intent i = getIntent();
        String key = i.getStringExtra("main");
        Integer value = Integer.parseInt(key);
        i.putExtra("main",value);
        i.putExtra("second",200);
        Toast.makeText(this, key, Toast.LENGTH_LONG).show();
    }
}
