package com.example.test.my_intent3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) { // 기존 것을 파과하고 새로 만들었다.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
    }

    public void buttonNext(View view) {
        Intent i = new Intent(this, SecondActivity.class); // 명시적 인텐트
        startActivity(i); // 안드로이드에게 화면을 만들어 달라고 요청한다.
    }

    public void buttonBefore(View view) {
        finish(); // 종료
    }

    public void buttonOther(View view) {
        Intent i = new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP); // 이 Intent는 onNewIntent로 보낸다.
        i.putExtra("product", 1004);
        startActivity(i);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            int productKey = intent.getIntExtra("product", 1001);
            Toast.makeText(this, "productKey:"+productKey, Toast.LENGTH_SHORT).show();
        }
    }
}
