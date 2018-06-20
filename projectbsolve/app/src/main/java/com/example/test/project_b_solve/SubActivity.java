package com.example.test.project_b_solve;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Locale;

public class SubActivity extends AppCompatActivity {
    ImageView imageView;
    TextView textView;
    RadioButton selectedLang;

    int langId;
    int count = 0;
    private TextToSpeech tts;
    String language;
    private Handler handler;

    class fruitInfo {

        Integer fruit;
        String fruitKorName;
        String fruitEngName;

        fruitInfo(int fruit, String fruitKorName, String fruitEngName) {
            this.fruit = fruit;
            this.fruitKorName = fruitKorName;
            this.fruitEngName = fruitEngName;
        }
    }

    private fruitInfo[] fruitInfos = {
            new fruitInfo(R.drawable.apple, "사과", "Apple"),
            new fruitInfo(R.drawable.banana, "바나나", "banana"),
            new fruitInfo(R.drawable.kiwi, "키위", "kiwi"),
            new fruitInfo(R.drawable.coconut, "코코넛", "coconut"),
            new fruitInfo(R.drawable.lemon, "레몬", "lemon"),
            new fruitInfo(R.drawable.orange, "오렌지", "orange"),
            new fruitInfo(R.drawable.strawberry, "딸기", "strawberry"),

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        Intent tmp = getIntent();
//        langId = tmp.getIntExtra("lang",0);
        language = tmp.getStringExtra("lang");

        //휴대폰 볼륨컨트롤이 반영되게 한다.
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        maxVolume = maxVolume / 2;
        am.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, 0);
        handler = new Handler();
        imageView = findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView2);


        imageView.setImageResource(fruitInfos[count].fruit);
        switch (language) {
            case "kor":
                textView.setText(fruitInfos[count].fruitKorName);
                break;
            case "eng":
                textView.setText(fruitInfos[count].fruitEngName);
                break;
        }

        tts = new TextToSpeech(SubActivity.this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    switch (language) {
                        case "kor":
                            tts.setLanguage(Locale.KOREA);
                            break;
                        case "eng":
                            tts.setLanguage(Locale.ENGLISH);
                            break;
                    }
                }
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (language) {
                    case "kor":
                        tts.speak(fruitInfos[count].fruitKorName, TextToSpeech.QUEUE_FLUSH, null);
                        break;
                    case "eng":
                        tts.speak(fruitInfos[count].fruitEngName, TextToSpeech.QUEUE_FLUSH, null);
                        break;
                }
            }
        });
    }

    public void goToNext(View view) {
        if (count < fruitInfos.length - 1) {
            count++;
            imageView.setImageResource(fruitInfos[count].fruit);
            switch (language) {
                case "kor":
                    textView.setText(fruitInfos[count].fruitKorName);
                    break;
                case "eng":
                    textView.setText(fruitInfos[count].fruitEngName);
                    break;
            }
        } else {
            finish();
        }
    }

    public void goToPrevious(View view) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tts != null) {
            tts.shutdown();
        }
    }
}
