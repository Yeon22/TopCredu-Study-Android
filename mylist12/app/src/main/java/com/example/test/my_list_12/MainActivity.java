package com.example.test.my_list_12;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //자원 변수 선언하기
    private EditText nation;
    private ListView lv;

    private ArrayList<String> nations = new ArrayList<String>();

    public MainActivity() {
        nations.add("한국");
        nations.add("일본");
        nations.add("중국");
        nations.add("미국");
        nations.add("러시아");
    }

    private ArrayAdapter adapter;
    private InputMethodManager imm; // 소프트 키보드 제어 객체

    private SwipeDetector swipeDetector = new SwipeDetector();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        //자원 불러오기
        nation = findViewById(R.id.nation);
        lv = findViewById(R.id.lv);

        //리스트뷰에 어대텁 장착하기
        adapter = new ArrayAdapter<String>(
                // simple_list_item_single_choice : 라디오 버튼
                this, android.R.layout.simple_list_item_single_choice, nations);
        lv.setAdapter(adapter);

        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        //리스트뷰에 롱클릭 리스너를 장착하기
        //롱클릭 시 삭제하기 기능을 넣으세요.
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           final int position, long id) {

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("삭제 확인")
                        .setMessage("정말 삭제하시겠습니까?")
                        .setPositiveButton("네, 삭제합니다.",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        nations.remove(position);
                                        adapter.notifyDataSetChanged();
                                    }
                                })
                        .setNegativeButton("아니요, 삭제하지 않습니다.",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                })
                        .show();

                return true;
            }
        });

        lv.setOnTouchListener(swipeDetector);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (swipeDetector.swipeDetected()) {
                    if (swipeDetector.getAction() == SwipeDetector.Action.RL) {
                        Toast.makeText(MainActivity.this,
                                "SwipeDetector.Action.RL", Toast.LENGTH_SHORT).show();
                    } else if (swipeDetector.getAction() == SwipeDetector.Action.LR) {
                        Toast.makeText(MainActivity.this,
                                "SwipeDetector.Action.LR", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //에디트텍스트에 키리스너를 장착하기
        nation.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.d("XXX", ""+keyCode);
                Log.d("XXX", ""+event.getAction());

                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    if (event.getAction() == KeyEvent.ACTION_DOWN) {
                        String stringNation = nation.getText().toString();
                        nation.setText("");

                        if (!stringNation.equals("")) {
                            nations.add(stringNation);
                            //어댑터에게 데이터가 변경된 것을 알려줍니다.(리스트뷰 리프레쉬 == 화면 갱신)
                            adapter.notifyDataSetChanged();
                        }
                        // 코드적으로 소프트키보드를 감춘다.
                        imm.hideSoftInputFromWindow(nation.getWindowToken(), 0);

                        //이벤트를 소비했으니 이 후에 작업을 하지 말아라는 뜻 입니다.
                        // (원래 작업: 엔터 = 줄바꿈)
                        return true;
                    }
                }
                return false;
            }
        });
    }
}
