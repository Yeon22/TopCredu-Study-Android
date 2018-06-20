package com.example.test.my_list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity1 extends AppCompatActivity {

    // 사용할 자원 변수 선언하기
    private Button button1;
    private Button button2;
    private ListView listView;

    private String[] nations = {"korea", "japan", "china", "russia"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 사용할 자원 불러오기
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        listView = findViewById(R.id.listView);

        // 리스트뷰에 어댑터 설정하기
        listView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, nations));
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        // 리스뷰에 클릭 리스너 설정하기
        // 선택된 아이템을 토스트로 띄우기
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(MainActivity1.this, "nation: " + nations[position], Toast.LENGTH_LONG)
                        .show();
            }
        });

        // 버튼 2개에 클릭리스너 설정하기
        // 멀티 초이스된 대상을 토스트와 로그로 확인하세요.
        button1.setOnClickListener(new View.OnClickListener() {

            // 한번이라도 체크된 대상
            @Override
            public void onClick(View v) {
                int count = listView.getCheckedItemCount();

                Toast.makeText(MainActivity1.this, "count: " + count, Toast.LENGTH_LONG).show();

                // SparseBooleanArray 배열이지만 리스트처럼 생각하자
                SparseBooleanArray array = listView.getCheckedItemPositions();

                for (int i = 0; i < array.size(); i++) {
                    int selected = array.keyAt(i);
                    Log.d("xxx", "position: " + selected + ", nation: " + nations[selected]);
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            // 현재 체크된 대상
            @Override
            public void onClick(View v) {
                int count = listView.getCheckedItemCount();

                Toast.makeText(MainActivity1.this, "count: " + count,
                        Toast.LENGTH_LONG).show();

                SparseBooleanArray array = listView.getCheckedItemPositions();

                for (int i = 0; i < nations.length; i++) {
                    if (array.valueAt(i)) { // 현재 체크된 상태일 때
                        int selected = array.keyAt(i);
                        Log.d("xxx", "position: " + selected + ", nation: " + nations[selected]);
                    }
                }
            }
        });

    }
}
