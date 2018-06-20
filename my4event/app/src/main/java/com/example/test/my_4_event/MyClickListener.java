package com.example.test.my_4_event;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MyClickListener implements View.OnClickListener {
    Context context;

    public MyClickListener() {}

    public MyClickListener(Context context) {
        this.context = context;
    }

    @Override

    public void onClick(View v) {
        if (v.getId() == R.id.button4) {
            alert(v, "Click");
        } else if (v.getId() == R.id.button5) {
            alert(v, "Click");
        } else if (v.getId() == R.id.button6) {
            alert(v, "Click");
        }
    }

    public void alert(View view, String txt) {
        if (context != null) {
            Toast.makeText(context, txt + " : #1 : " + ((Button)view).getText().toString(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(view.getContext(), txt + " : #2 : " + ((Button)view).getText().toString(), Toast.LENGTH_LONG).show();
        }
    }
}
