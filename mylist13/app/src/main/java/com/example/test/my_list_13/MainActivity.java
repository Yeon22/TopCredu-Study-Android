package com.example.test.my_list_13;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private ListView lv;
    private ArrayList<Hero> heroList = new ArrayList<Hero>();

    public MainActivity() {
        heroList.add(new Hero("아이언맨", 35));
        heroList.add(new Hero("토르", 33));
        heroList.add(new Hero("헐크", 41));
        heroList.add(new Hero("스파이더맨", 21));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(new HeroAdapter());
    }

    private class HeroAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return heroList.size();
        }

        @Override
        public Hero getItem(int position) {
            return heroList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public HeroCell getView(int position, View convertView, ViewGroup parent) {
            HeroCell cell = null;
            //메모리를 아끼기 위해서 돌려막기용 뷰 객체
            // 뷰를 만드는 코드
            if (convertView == null) {
                Log.d(TAG, "getView: converView == null");
                cell = new HeroCell(MainActivity.this);
            } else {
                Log.d(TAG, "getView: convertView != null");
                cell = (HeroCell) convertView;
            }
            // Hero 객체를 건네주면 뷰에 문자열이 할당되는 작업이 수행된다.
            cell.setContent(getItem(position));
            return cell;
        }

    }
}
