package com.example.test.my_list_14;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ContactCell extends LinearLayout {
    private ImageView ivPhoto;
    private TextView tvName;
    private TextView tvAlias;
    private TextView tvMessage;

    public ContactCell(Context context) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.contact_cell, this, true);

        ivPhoto = (ImageView) findViewById(R.id.ivPhoto);
        tvName = (TextView) findViewById(R.id.tvName);
        tvAlias = (TextView) findViewById(R.id.tvAlias);
        tvMessage = (TextView) findViewById(R.id.tvMessage);
    }

    public void setContent(Contact contact) {
        ivPhoto.setImageResource(contact.getPhoto());
        tvName.setText(contact.getName());
        tvAlias.setText(contact.getAlias());
        tvMessage.setText(contact.getMessage());
    }
}
