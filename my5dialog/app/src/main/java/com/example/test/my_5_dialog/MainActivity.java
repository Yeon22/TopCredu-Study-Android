package com.example.test.my_5_dialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
    }

    public void button1Click(View view) {
        // 설정
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("상단 제목");
        builder.setMessage("가운데 문자열");
        builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textView.setText("button1 # 네");
            }
        });
        builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textView.setText("button1 # 취소");
            }
        });
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textView.setText("button1 # 아니오");
            }
        });
        // 기동
        builder.setCancelable(false); // 백버튼 작동 중지
        builder.show();
    }

    public void button2Click(View view) {
        View v = View.inflate(this, R.layout.layout, null);
        ImageView imageView = v.findViewById(R.id.imageView);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("사과를 좋아하시나요?");
//        builder.setMessage("가운데 문자열");
        builder.setView(v);

        builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textView.setText("button2 # 네");
            }
        });

        final AlertDialog dialog = builder.show();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Apple");
                dialog.dismiss();
            }
        });
    }

    public void button3Click(View view) {
        final int random = new Random().nextInt(2);
        View v = View.inflate(this, R.layout.layout2, null);
        ImageView imageViewApple = v.findViewById(R.id.imageViewApple);
        ImageView imageViewBanana = v.findViewById(R.id.imageViewBanana);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (random == 0) {
            builder.setTitle("Apple");
        } else if (random == 1) {
            builder.setTitle("Banana");
        }
        builder.setView(v);

        final AlertDialog dialog = builder.show();

        imageViewApple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (random == 0) {
                    textView.setText("O 정답!");
                    dialog.dismiss();
                } else {
                    textView.setText("X 오답!");
                    dialog.dismiss();
                }
            }
        });

        imageViewBanana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(random == 1) {
                    textView.setText("O 정답!");
                    dialog.dismiss();
                } else {
                    textView.setText("X 오답!");
                    dialog.dismiss();
                }
            }
        });
    }
}