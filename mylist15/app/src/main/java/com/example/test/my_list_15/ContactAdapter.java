package com.example.test.my_list_15;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public final class ContactAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Contact> contactList;

    public ContactAdapter(Context context, ArrayList<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    public static final class ContactType {
        public static final int CONTACT_WITH_IMAGE = 1;
        public static final int CONTACT_WITH_NO_IMAGE = 0;

        public static final int CONTACT_TYPE_COUNT = 2;
    }

    // ----------------------------------------------------------
    // 아이템 디자인이 여러개인 경우 추가로 2개의 메소드를 더 작성한다.
    @Override
    public int getItemViewType(int position) {
        return contactList.get(position).getContactType();
    }

    @Override
    public int getViewTypeCount() {
        return ContactType.CONTACT_TYPE_COUNT;
    }
    // ------------------------------------------------------------

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        int type = getItemViewType(position);

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);

            if (type == ContactType.CONTACT_WITH_IMAGE) {
                v = inflater.inflate(R.layout.image_contact_layout_left, parent, false);
            } else {
                v = inflater.inflate(R.layout.image_contact_layout_right, parent, false);
            }
        }

        Contact c = contactList.get(position);

        if (type == ContactType.CONTACT_WITH_IMAGE) {
            ImageView img = (ImageView) v.findViewById(R.id.img);
            TextView name = (TextView) v.findViewById(R.id.name);
            TextView surname = (TextView) v.findViewById(R.id.surname);
            TextView email = (TextView) v.findViewById(R.id.email);

            img.setImageResource(c.getImageId());
            name.setText(c.getName());
            surname.setText(c.getSurname());
            email.setText(c.getEmail());
        } else {
            TextView name = (TextView) v.findViewById(R.id.name);
            TextView surname = (TextView) v.findViewById(R.id.surname);
            TextView email = (TextView) v.findViewById(R.id.email);

            name.setText(c.getName());
            surname.setText(c.getSurname());
            email.setText(c.getEmail());
        }

        return v;
    }

}
