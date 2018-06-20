package com.example.test.my_intent2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Handler handler;

    private TextView tv;
    private ImageView iv;
    private ListView lv;

    ArrayList<Uri> imageUris = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();

        tv = (TextView) findViewById(R.id.textView);
        iv = (ImageView) findViewById(R.id.imageView);
        lv = (ListView) findViewById(R.id.listView);

        Intent intent = getIntent(); // 다른 액티비티가 보낸 파라미터를 받는다.
        String action = intent.getAction();
        String type = intent.getType();

        // 받은 파라미터가 1개일 때
        if (Intent.ACTION_SEND.equals(action) && type != null) { // 한개를 보냈을 때
            if ("text/plain".equals(type)) { // 받은 데이터가 문자열일 때
                handleSendText(intent);
            } else if (type.startsWith("image/")) { // 받은 데이터가 이미지일 때
                handleSendImage(intent);
            }
        }
        // 받은 파라미터가 다수일 때
        else if (Intent.ACTION_SEND_MULTIPLE.equals(action) && type != null) { // 여러개를 보냈을 때
            if (type.startsWith("image/")) {
                handleSendMultipleImage(intent);
            }
        }
    }

    private void handleSendMultipleImage(Intent intent) {
        imageUris = intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
        if (imageUris != null) {
            lv.setAdapter(new CustomAdapter());
        }
    }

    private void handleSendImage(Intent intent) {
        final Uri imageUri = intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (imageUri != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    iv.setImageURI(imageUri);
                }
            });
        }
    }

    private class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return imageUris.size();
        }

        @Override
        public Uri getItem(int position) {
            return imageUris.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public ImageView getView(int position, View convertView, ViewGroup parent) {
            ImageView iv = null;
            if (convertView == null) {
                iv = new ImageView(getApplicationContext());
            } else {
                iv = (ImageView) convertView;
            }
            iv.setImageURI(getItem(position));
            return iv;
        }

    }

    private void handleSendText(Intent intent) {
        // 다른 액티비티가 보낸 문자열을 꺼낸다.
        // 타 액티비티가 보낸 키값을 알 수 없으므로 대신 Intent.EXTRA_TEXT 상수를 설정한다.
        final String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    tv.setText(sharedText);
                }
            });
        }
    }
}
