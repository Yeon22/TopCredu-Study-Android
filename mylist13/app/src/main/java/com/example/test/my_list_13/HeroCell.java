package com.example.test.my_list_13;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HeroCell extends LinearLayout {

    private TextView heroName;
    private TextView heroAge;

    public HeroCell(Context context) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.hero_cell, this, true);

        heroName = (TextView) findViewById(R.id.heroName);
        heroAge = (TextView) findViewById(R.id.heroAge);

    }

    public void setContent(Hero hero) {
        heroName.setText(hero.getName());
        heroAge.setText(hero.getAge() + "");
    }
}
