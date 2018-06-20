package com.example.test.project_b_solve;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radioGroup);
    }

    public void start(View view) {

        Intent i = new Intent(this, SubActivity.class);
        radioGroup = findViewById(R.id.radioGroup);
        int tmp = radioGroup.getCheckedRadioButtonId();
        switch (tmp) {
            case R.id.radioKor:
                i.putExtra("lang", "kor");
                break;
            case R.id.radioEng:
                i.putExtra("lang", "eng");
                break;
        }
        startActivity(i);
    }
}
