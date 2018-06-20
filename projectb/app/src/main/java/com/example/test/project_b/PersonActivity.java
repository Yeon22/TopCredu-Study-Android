package com.example.test.project_b;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PersonActivity extends AppCompatActivity {
    TextView textView;
    ImageView imageView;
    int i = 0;
    String[] jobsName = {
            "요리사", "의사", "소방관", "피아니스트", "경찰", "가수"
    };
    Integer[] jobs = {
            R.drawable.chef,
            R.drawable.doctor,
            R.drawable.firefighter,
            R.drawable.pianist,
            R.drawable.police,
            R.drawable.singer
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        imageView = findViewById(R.id.imageViewJob);
        textView = findViewById(R.id.textView);
    }

    public void backJob(View view) {
        try {
            i--;
            imageView.setImageResource(jobs[i]);
            textView.setText(jobsName[i]);
        } catch (Exception e) {
            finish();
        }
    }

    public void nextJob(View view) {
        try {
            i++;
            imageView.setImageResource(jobs[i]);
            textView.setText(jobsName[i]);
        } catch(Exception e) {
            i = 0;
            imageView.setImageResource(jobs[i]);
            textView.setText(jobsName[i]);
        }
    }
}
