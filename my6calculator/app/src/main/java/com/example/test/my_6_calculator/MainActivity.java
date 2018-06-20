package com.example.test.my_6_calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TableLayout table;
    private TextView resultBox;
    private String resultBoxString;

    private float storedValue = 0;

    private enum Operation {
        plus, minus, multiply, divide
    }

    private Operation operation;

    private int width;
    private int height;

    private ButtonClicker buttonClicker = new ButtonClicker();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);

        table = findViewById(R.id.table);
        resultBox = findViewById(R.id.resultsBox);

        findWindowSize();
        resizeTable();
        resizeResultBox();
    }

    private void resizeResultBox() {
        resultBox.setHeight(height / 5);
        resultBox.setWidth(width * 3 / 4);
        resultBox.setTextSize(TypedValue.COMPLEX_UNIT_PX, height / 10);
        resultBox.setText("");

        resultBoxString = "";
    }

    private void resizeTable() {

        for (int i = 0; i < table.getChildCount(); i++) { // table의 자식 -> TableRow
            TableRow row = (TableRow) table.getChildAt(i);

            for (int j = 0; j < row.getChildCount(); j++) {
                if (row.getChildAt(j) instanceof Button) { // 지금 가리키는 자식 객체가 Button 인가?
                    Button button = (Button) row.getChildAt(j);
                    resizeButton(button); // 휴대폰 크기에 따라 버튼 크기 조정
                    addClickListener(button);
                }
            }
        }
    }

    private void resizeButton(Button button) {
        button.setHeight(height / 5);
        button.setWidth(width / 4);
        // 구한 화면사이즈 단위가 픽셀이므로 TypeValue.COMPLEX_UNIT_PX 파라미터로
        // 처리단위를 알려줄 필요가 있다.
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
                    storedValue = 0;
                    break;
                case R.id.plus:
                    operation = Operation.plus;
                    storedValue = resultsBoxFloat();
                    resultBoxString = "";
                    break;
                case R.id.minus:
                    if (!resultBoxString.equals("")) {
                        operation = Operation.minus;
                        storedValue = resultsBoxFloat();
                        resultBoxString = "";
                    } else {
                        resultBoxString = "-";
                    }
                    break;
                case R.id.multiply:
                    operation = Operation.multiply;
                    storedValue = resultsBoxFloat();
                    resultBoxString = "";
                    break;
                case R.id.divide:
                    operation = Operation.divide;
                    storedValue = resultsBoxFloat();
                    resultBoxString = "";
                    break;
                case R.id.equals:
                    resultBoxString = getOutcome();
                    break;
            }

            resultBox.setText(resultBoxString);
        }

    }

    public float resultsBoxFloat() {
        if (!resultBoxString.equals("")) {
            return Float.parseFloat(resultBoxString);
        } else {
            return 0;
        }
    }

    public String getOutcome() {

        switch (operation) {
            case plus:
                storedValue = storedValue + resultsBoxFloat();
                break;
            case minus:
                storedValue = storedValue - resultsBoxFloat();
                break;
            case multiply:
                storedValue = storedValue * resultsBoxFloat();
                break;
            case divide:
                storedValue = storedValue / resultsBoxFloat();
                break;
        }
        return Float.toString(storedValue);
    }

}
