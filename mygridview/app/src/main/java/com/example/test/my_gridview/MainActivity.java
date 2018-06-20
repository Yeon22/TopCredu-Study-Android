package com.example.test.my_gridview;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Integer[] fruits = {
            R.drawable.apple,
            R.drawable.banana,
            R.drawable.coconut,
            R.drawable.kiwi,
            R.drawable.lemon,
            R.drawable.orange,
            R.drawable.strawberry
    };

    private GridView gridView;
    private ImageView selectedIv = null;
    private TextToSpeech tts; // 글자를 소리로 바꿔주는 기술
    // 별도의 스레드가 화면을 처리하는 메인스레드에게 화면 갱신을 부탁할 때
    // 의사를 전달하는 기능의 클래스.
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //휴대폰 볼륨컨트롤이 반영되게 한다.
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        maxVolume = maxVolume / 2;
        // 시작할 때 볼륨을 적절히 지정하는 방법.
        am.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, 0);

        // 핸들러 객체를 만드는 코드의 위치에 따라 해당 핸들러는 렉시컬스코프의 스레드로 전달하는 작업을 수행한다.
        handler = new Handler();

        gridView = (GridView) findViewById(R.id.gridview);
        // ListAdapter 인터페이스 구현체인 CustomAdapter 객체를 전달하면
        // 안드로이드가 CustomAdapter 객체를 사용하여 컨테이너에 뷰들을 장착한다.
        gridView.setAdapter(new CustomAdapter());

        // gridView 가지고 있는 모든 Item에 클릭 이벤트를 적용하라.
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                if (selectedIv != null) {
                    ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) selectedIv.getLayoutParams();
                    params.width = 200;
                    params.height = 200;
                    selectedIv.setLayoutParams(params);
                }

                ImageView iv = (ImageView) view;
                selectedIv = iv;

                ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) iv.getLayoutParams();
                params.width = 250;
                params.height = 250;
                iv.setLayoutParams(params);


                String name = getResources().getResourceName(fruits[position]);
                //패키지명:drawable/apple
                debug(name);
                name = name.split("/")[1];

//                name = "청와대는 4일 정윤회 국정개입 감찰보고서의 유출 경위와 관련해 " +
//                        "검찰 수사에서 잘 밝혀질 것이라며 신중한 모습을 보였다. " +
//                        "민경욱 청와대 대변인은 이날 오전 기자들과 만나 ";
                tts.speak(name, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        // TTS엔진은 안드로이드에 기본적으로 장착되어있다.
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) { // 휴대폰에 TTS엔진이 있는지 체크.
                    tts.setLanguage(Locale.ENGLISH); // 영어발음으로 나온다.
                }
            }
        });
    }

    protected void debug(final String data) {
        handler.post(new Runnable() {

            @Override
            public void run() {
                Log.d("xxx", "" + data);
                Toast.makeText(MainActivity.this, "" + data, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class CustomAdapter extends BaseAdapter {
        // 어댑터가 처리해야 할 개수가 몇개인지 알려준다.
        @Override
        public int getCount() {
            return fruits.length;
        }

        // 해당 인덱스에서 사용할 데이터가 무엇인가?
        @Override
        public Object getItem(int position) {
            return fruits[position];
        }

        // 해당 인덱스에서 사용할 키 값이 무엇인가?
        @Override
        public long getItemId(int position) {
            return position;
        }

        // 해당 인덱스에서 사용할 뷰가 무엇인가?
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView iv;
            if (convertView == null) {
                iv = new ImageView(MainActivity.this);
                iv.setLayoutParams(new GridView.LayoutParams(200, 200));
                iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                iv.setPadding(5, 5, 5, 5);
            } else {
                iv = (ImageView) convertView;
            }
            iv.setImageResource(fruits[position]);
            return iv;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tts != null) {
            tts.shutdown();
        }
    }
}
