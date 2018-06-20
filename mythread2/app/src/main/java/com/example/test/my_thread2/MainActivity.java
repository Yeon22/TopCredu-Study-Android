package com.example.test.my_thread2;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView text_count;
    private Button btn_count;
    private TextView text_count_auto;

    private int count = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_count = (TextView) findViewById(R.id.text_count);
        btn_count = (Button) findViewById(R.id.btn_count);
        text_count_auto = (TextView) findViewById(R.id.text_count_auto);

        Thread countThread = new Thread("count thread") {
            @Override
            public void run() {
                for (int i = 0; i < 30; i++) {
                    count++;

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    /**
                     * CalledFromWrongThreadException:
                     * Only the original thread that created a view
                     * hierarchy can touch its views.
                     * 해결책: Handler 사용
                     */
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            text_count_auto.setText("" + count);
                        }
                    });

                }
            }
        };
        countThread.start();

        btn_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_count.setText("" + count);
            }
        });
    }
}
