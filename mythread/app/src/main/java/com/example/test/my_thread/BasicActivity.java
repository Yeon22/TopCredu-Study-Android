package com.example.test.my_thread;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BasicActivity extends AppCompatActivity {
    TextView textView;
    // 메인 스레드가 핸들러를 만들었으므로 이 핸들러를 사용하면 메인스레드의 큐에 요청사항을 전달한다.
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        textView = findViewById(R.id.textView);

//        count(10);

        // 다른 스레드에게 일처리를 요청한다.
        Thread t1 = new MyThread();
        t1.setName("T1");
        t1.start(); // 새 스레드를 생성 >> 그 스레드가 run 메소드 로직을 수행

        Thread t2 = new Thread(new MyRunnable());
        t2.setName("T2");
        t2.start(); // 새 스레드를 생성 >> 새 스레드가 MyRunnable 객체의 run 메소드 로직을 수행
    }

    int i = 1;
    private synchronized void count(int max) { // 스레드가 도는 중에 다른 스레드가 침범하지 못한다.
        final String who = Thread.currentThread().getName();
        for (i = 1; i <= max; i++) {
            // 타 스레드에서 화면을 접근하는 것은 금지되어 있다.
            // 타 스레드는 메인스레드에게 요청하고 메인스레드가 화면을 제어해야 한다.
            // textView.setText(who + " : " + i);

            // 타 스레드가 화면을 관장하는 메인스레드에게 요청하는 방식의 코드다.
            handler.post(new Runnable() {
                @Override
                public void run() {
                    textView.setText(who + " : " + i);
                }
            });

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 메인스레드는 시간이 오래걸리는 작업을 하면 안된다.
        // 안드로이드는 네트워크 관련 로직은 무조건 개별 스레드에서 처리하도록 강제한다.
//        count(10);
    }

    public class MyThread extends Thread {
        @Override
        public void run() {
            count(10);
        }
    }

    public class MyRunnable implements Runnable {
        @Override
        public void run() {
            count(10);
        }
    }
}
