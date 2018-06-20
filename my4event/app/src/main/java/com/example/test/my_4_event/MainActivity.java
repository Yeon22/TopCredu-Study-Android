package com.example.test.my_4_event;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button2;
    Button button3;

    Button button4;
    Button button5;
    Button button6;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button2:
                alert(v, "Click");
                break;
            case R.id.button3:
                alert(v, "Click");
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);

        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                alert(v, "LongClick");
//                return false;
                return true; // Click 이벤트 작동시키지 않는다.
            }
        });

        MyClickListener myClickListener = new MyClickListener(this);
        button4.setOnClickListener(myClickListener);
        button5.setOnClickListener(myClickListener);
        button6.setOnClickListener(myClickListener);
    }

    public void button1Click(View view) {
        alert(view, "Click");
    }

    public void alert(View view, String txt){
        Toast.makeText(this, txt + ":" + ((Button)view).getText().toString(), Toast.LENGTH_LONG).show();
    }
}
