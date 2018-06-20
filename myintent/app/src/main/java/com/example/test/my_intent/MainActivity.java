package com.example.test.my_intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setAction("i.am.a.king"); // 암시적 인텐트
        //intent.addCategory(""); //안드로이드가 DEFAULT 카테고리를 자동으로 추가한다.

        startActivity(intent);
    }
}
