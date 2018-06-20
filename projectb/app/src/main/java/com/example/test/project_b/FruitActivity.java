package com.example.test.project_b;

import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FruitActivity extends AppCompatActivity {
    TextView textView;
    ImageView imageView;
    ImageView selectedImageView;
    int i = 0;
    String[] fruitsName = {
            "사과", "바나나", "코코넛", "키위", "레몬", "오렌지", "딸기"
    };
    Integer[] fruits = {
            R.drawable.apple,
            R.drawable.banana,
            R.drawable.coconut,
            R.drawable.kiwi,
            R.drawable.lemon,
            R.drawable.orange,
            R.drawable.strawberry
    };
    private TextToSpeech tts;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        maxVolume = maxVolume / 2;
        am.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, 0);

        handler = new Handler();

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
    }

    protected void debug(final String data) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Log.d("xxx", "" + data);
                Toast.makeText(FruitActivity.this, "" + data, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void backFruit(View view) {
        try {
            i--;
            imageView.setImageResource(fruits[i]);
            textView.setText(fruitsName[i]);
        } catch (Exception e) {
            finish();
        }
    }

    public void nextFruit(View view) {
        try {
            i++;
            imageView.setImageResource(fruits[i]);
            textView.setText(fruitsName[i]);
        } catch(Exception e) {
            i = 0;
            imageView.setImageResource(fruits[i]);
            textView.setText(fruitsName[i]);
        }
    }
}
