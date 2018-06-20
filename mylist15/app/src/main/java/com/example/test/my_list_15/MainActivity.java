package com.example.test.my_list_15;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.lv);

        adapter = new ContactAdapter(this, createContact(20));
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "position: " + position, Toast.LENGTH_LONG).show();
            }
        });
    }

    // 더미 데이터 생성
    private ArrayList<Contact> createContact(int count) {
        ArrayList<Contact> contactList = new ArrayList<Contact>();

        Contact contact = null;
        for (int i = 0; i < count; i++) {
            contact = new Contact();

            if ((i % 2) == 0) {
                contact.setContactType(ContactAdapter.ContactType.CONTACT_WITH_NO_IMAGE)
                        .setEmail("이메일").setName("이름").setSurname("성명");
            } else {
                contact.setContactType(ContactAdapter.ContactType.CONTACT_WITH_IMAGE);
                contact.setEmail("kyssra@gamil.com");
                contact.setImageId(R.drawable.apple);
                contact.setName("Chris");
                contact.setSurname("Song");
            }

            contactList.add(contact);
        }

        return contactList;
    }
}
