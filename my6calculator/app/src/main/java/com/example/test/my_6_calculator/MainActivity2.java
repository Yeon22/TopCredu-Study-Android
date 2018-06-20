package com.example.test.my_6_calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    /*
    과제 : (), ^, | 버튼을 추가한다.
    9patch 이미지를 버튼에 적용한다.
     */

    private TableLayout table;
    private TextView resultBox;
    private String resultBoxString;

    private int width;
    private int height;

    private ButtonClicker buttonClicker = new ButtonClicker();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);

        table = (TableLayout) findViewById(R.id.table);
        table.requestFocus();
        resultBox = (TextView) findViewById(R.id.resultsBox);

        findWindowSize();
        resizeTable();
        resizeResultBox();
    }

    private void resizeResultBox() {
        resultBox.setHeight(height / 5);
        resultBox.setWidth(width * 3 / 4);
        resultBox.setTextSize(TypedValue.COMPLEX_UNIT_PX, height / 20);
        resultBox.setText("");
        resultBoxString = "";
    }

    private void resizeTable() {

        for (int i = 0; i < table.getChildCount(); i++) {
            TableRow row = (TableRow) table.getChildAt(i);

            for (int j = 0; j < row.getChildCount(); j++) {
                if (row.getChildAt(j) instanceof Button) {
                    Button button = (Button) row.getChildAt(j);
                    resizeButton(button);
                    addClickListener(button);
                }
            }
        }
    }

    private void resizeButton(Button button) {
        button.setHeight(height / 5);
        button.setWidth(width / 4);
        button.setTextSize(TypedValue.COMPLEX_UNIT_PX, height / 10);
    }

    private void addClickListener(Button button) {
        button.setOnClickListener(buttonClicker);
    }

    private void findWindowSize() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        width = metrics.widthPixels;
        height = metrics.heightPixels;
    }

    private class ButtonClicker implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.b0:
                    resultBoxString += "0";
                    break;
                case R.id.b1:
                    resultBoxString += "1";
                    break;
                case R.id.b2:
                    resultBoxString += "2";
                    break;
                case R.id.b3:
                    resultBoxString += "3";
                    break;
                case R.id.b4:
                    resultBoxString += "4";
                    break;
                case R.id.b5:
                    resultBoxString += "5";
                    break;
                case R.id.b6:
                    resultBoxString += "6";
                    break;
                case R.id.b7:
                    resultBoxString += "7";
                    break;
                case R.id.b8:
                    resultBoxString += "8";
                    break;
                case R.id.b9:
                    resultBoxString += "9";
                    break;
                case R.id.bdot:
                    if (!resultBoxString.contains(".")) {
                        resultBoxString += ".";
                    }
                    break;
                case R.id.clear:
                    resultBoxString = "";
                    break;
                case R.id.path1:
                    resultBoxString += "(";
                    break;
                case R.id.path2:
                    resultBoxString += ")";
                    break;
                case R.id.invol:
                    resultBoxString += "^";
                    break;
                case R.id.root:
                    resultBoxString += "|";
                    break;
                case R.id.plus:
                    resultBoxString += "+";
                    break;
                case R.id.minus:
                    resultBoxString += "-";
                    break;
                case R.id.multiply:
                    resultBoxString += "*";
                    break;
                case R.id.divide:
                    resultBoxString += "/";
                    break;
                case R.id.equals:
                    try {
                        resultBoxString = getResult(resultBoxString);
                        resultBoxString = fmt(Double.parseDouble(resultBoxString));
                    } catch (Exception e) {
                        resultBoxString = resultBoxString + " = 연산오류";
                    }
                    break;
            }

            resultBox.setText(resultBoxString);
        }

    }

    BigDecimalCalculator go = new BigDecimalCalculator();

    public String getResult(String txt) {
        return go.brackets(txt);
    }

    public String fmt(double d) {
        if (d == (long) d)
            return String.format("%d", (long) d);
        else
            return String.format("%s", d);
    }

}
