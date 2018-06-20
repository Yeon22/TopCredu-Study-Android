package com.example.test.project_c;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class ContactAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Contact> contactList;

    public ContactAdapter(Context context, ArrayList<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    public static final class ContactType {
        public static final int CONTACT_IMAGE_LEFT = 0;
        public static final int CONTACT_IMAGE_RIGHT = 1;
        public static final int CONTACT_TYPE_COUNT = 2;
    }

    @Override
    public int getItemViewType(int position) {
        return contactList.get(position).getContactType();
    }

    @Override
    public int getViewTypeCount() {
        return ContactType.CONTACT_TYPE_COUNT;
    }

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

        if(v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if(type == ContactType.CONTACT_IMAGE_LEFT) {
                v = inflater.inflate(R.layout.image_layout_left, parent, false);
            } else {
                v = inflater.inflate(R.layout.image_layout_right, parent, false);
            }
        }

        Contact c = contactList.get(position);

        if(type == ContactType.CONTACT_IMAGE_LEFT) {
            ImageView img = v.findViewById(R.id.msgImageLeft);
            img.setImageResource(c.getImageId());
        } else {
            ImageView img = v.findViewById(R.id.msgImageRight);
            img.setImageResource(c.getImageId());
        }

        return v;
    }
}
