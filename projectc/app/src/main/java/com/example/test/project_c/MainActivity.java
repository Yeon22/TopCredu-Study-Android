package com.example.test.project_c;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        adapter = new ContactAdapter(this, createContact(20));
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "position : " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ArrayList<Contact> createContact(int count) {
        ArrayList<Contact> contactList = new ArrayList<Contact>();
        Contact contact = null;

        for (int i = 0; i < count; i++) {
            contact = new Contact();
            if((i % 2) == 0) {
                contact.setContactType(ContactAdapter.ContactType.CONTACT_IMAGE_RIGHT);
            } else {
                contact.setContactType(ContactAdapter.ContactType.CONTACT_IMAGE_LEFT);
            }

            contactList.add(contact);
        }

        return contactList;
    }
}
