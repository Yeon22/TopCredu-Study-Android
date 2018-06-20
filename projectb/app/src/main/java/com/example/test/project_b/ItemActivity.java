package com.example.test.project_b;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemActivity extends AppCompatActivity {
    TextView textView;
    ImageView imageView;
    int i = 0;
    String[] itemsName = {
            "빗자루", "계산기", "가위"
    };
    Integer[] items = {
            R.drawable.broom,
            R.drawable.calculator,
            R.drawable.scissors
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        imageView = findViewById(R.id.imageViewItem);
        textView = findViewById(R.id.textView);
    }

    public void backItem(View view) {
        try {
            i--;
            imageView.setImageResource(items[i]);
            textView.setText(itemsName[i]);
        } catch (Exception e) {
            finish();
        }
    }

    public void nextItem(View view) {
        try {
            i++;
            imageView.setImageResource(items[i]);
            textView.setText(itemsName[i]);
        } catch(Exception e) {
            i = 0;
            imageView.setImageResource(items[i]);
            textView.setText(itemsName[i]);
        }
    }
}
