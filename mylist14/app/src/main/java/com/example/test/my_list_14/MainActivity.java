package com.example.test.my_list_14;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private ArrayList<Contact> contactList = new ArrayList<Contact>();

    public MainActivity() {
        contactList.add(new Contact(R.drawable.contact_anony, "홍길동", "일지매", "쾌도 남아"));
        contactList.add(new Contact(R.drawable.contact_anony, "장길산", "산적", "후후후"));
        contactList.add(new Contact(R.drawable.contact_anony, "임거정", "의적", "안녕하세요"));
        contactList.add(new Contact(R.drawable.contact_anony, "구운몽", "플레이보이", "반갑습니다."));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(new ContactAdapter());
    }

    private class ContactAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return contactList.size();
        }

        @Override
        public Contact getItem(int position) {
            return contactList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public ContactCell getView(int position, View convertView, ViewGroup parent) {
            ContactCell cell = null;
            //메모리를 아끼기 위해서 돌려막기용 뷰 객체
            if (convertView == null) {
                cell = new ContactCell(MainActivity.this);
            } else {
                cell = (ContactCell) convertView;
            }
            cell.setContent(getItem(position));
            return cell;
        }

    }
}
