package com.example.test.acrivity_life_cycle;

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

        Intent i = getIntent();
        String key = i.getStringExtra("from");
        Toast t = Toast.makeText(this, "from:" + key, Toast.LENGTH_LONG);
        t.show();
    }

    public void popup(View view) {
        // Toast의 환경설정
        // context : 화면 처리 기술
        Toast t = Toast.makeText(this, "Second!", Toast.LENGTH_LONG);
        // Toast를 띄워라
        t.show();
    }

    public void back(View view) {
        Intent i = new Intent();
        i.putExtra("response","from SecondActivity"); // 응답하는 방법
        setResult(200, i);
        finish();
    }
}
