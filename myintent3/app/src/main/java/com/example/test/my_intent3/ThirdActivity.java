package com.example.test.my_intent3;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
    }

    public void buttonNext(View view) {
        // 첫 액티비티로 전환한다.
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("첫 액티비티로 전환할까요?");
        b.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(ThirdActivity.this, MainActivity.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); // 새로 만들지 않고 기존 것을 재활용
                startActivity(i);
            }
        });
        b.show();
    }

    public void buttonBefore(View view) {
        finish();
    }

    public void buttonOther(View view) {
    }
}
