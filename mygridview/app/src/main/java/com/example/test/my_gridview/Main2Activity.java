package com.example.test.my_gridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class Main2Activity extends AppCompatActivity {
    GridView gridview;
    String[] names = {
            "Apple", "Banana", "Kiwi"
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        gridview = findViewById(R.id.gridview);
//        gridview.setAdapter(new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, names)
//        );
        gridview.setAdapter(new CustomAdapter());

        // 클릭리스너를 장착한다.
        // 클릭하면 해당 이미지의 배경화면을 바꾼다.
        // 다른 이미지를 클릭하면 이전 이미지는 배경화면을 없애고
        // 클릭된 이미지의 배경화면을 바꾼다.
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
                iv = new ImageView(Main2Activity.this);
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
}
