package com.example.test.acrivity_life_cycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("LIFE-CYCLE", "1.onCreate");
        Toast.makeText(this, "1.onCreate", Toast.LENGTH_LONG).show();
        // Toast는 alert과 같음.
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("LIFE-CYCLE", "#.onRestart");
        Toast.makeText(this, "#.onRestart", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LIFE-CYCLE", "2.onStart");
        Toast.makeText(this, "2.onStart", Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LIFE-CYCLE", "3.onResume");
        Toast.makeText(this, "3.onResume", Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LIFE-CYCLE", "1.onPause");
        Toast.makeText(this, "1.onPause", Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LIFE-CYCLE", "2.onStop");
        Toast.makeText(this, "2.onStop", Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LIFE-CYCLE", "3.onDestroy");
        Toast.makeText(this, "3.onDestroy", Toast.LENGTH_LONG).show();
    }

    public void next(View view) {
        // 두 번째 액티비티를 호출한다. 명시적 인텐트 방법.
        Intent i = new Intent(this, SecondActivity.class);
        i.putExtra("from", "Hello World!"); // 파라미터를 전달하는 방법.
//        startActivity(i); // 안드로이드 프레임워크에 얘기 하는 것이다.
        startActivityForResult(i, 100); // 호출한 액티피티한테 결과 값을 받고 싶을 때
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) { // 요청자의 요청부분 코드
            if (resultCode == 200) { // 응답자의 응답부분 코드
                String response = data.getStringExtra("response");
                Toast.makeText(this, response, Toast.LENGTH_LONG).show();
            }
        }
    }
}
