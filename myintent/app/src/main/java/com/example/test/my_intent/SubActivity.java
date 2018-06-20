package com.example.test.my_intent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class SubActivity extends AppCompatActivity {

    private ListView lv;
    private List<String> menuList = Arrays.asList(
            "전화 바로 걸기", "문자 보내러 가기", "인터넷 앱 띄우기",
            "메일 보내러 가기", "문자 바로 보내기"
    );
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        i = new Intent();

        lv = (ListView) findViewById(R.id.lv);

        // 컨테이너에 뷰를 장착할 때 어댑터를 통해서 작업을 지시한다.
        // XML도 빌트인이 있다. -> android.R.layout.simple_list_item_1
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuList));

        // 리스트뷰에 가지고 있는 모든 아이템에 클릭리스너를 적용하라.
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {
                    case 0: //전화 바로 걸기
                        i.setAction(Intent.ACTION_CALL); // 전화앱에 manifest에 설정된 ACTION_CALL
                        i.setData(Uri.parse("tel:01064752951"));

                        startActivity(i);
                        break;
                    case 1: //문자 보내러 가기
                        i.setAction(Intent.ACTION_SENDTO);
                        i.setData(Uri.parse("sms:01064752951"));

                        startActivity(i);
                        break;
                    case 2: //인터넷 앱 띄우기
                        i.setAction(Intent.ACTION_VIEW);
                        i.setData(Uri.parse("http://www.google.com"));

                        startActivity(i);
                        break;
                    case 3: //메일 보내러 가기
                        String[] addrs = {"mailto:kyssra@gmail.com"};

                        i.setAction(Intent.ACTION_SEND);
                        i.putExtra(Intent.EXTRA_EMAIL, addrs);
                        i.putExtra(Intent.EXTRA_SUBJECT, "메일 제목");
                        i.putExtra(Intent.EXTRA_TEXT, "행운의 편지...");
                        i.setType("message/rfc822");

                        startActivity(i);
                        break;
                    case 4: //문자 바로 보내기
                        SmsManager sm = SmsManager.getDefault();
                        sm.sendTextMessage("01064752951", "보낸이", "문자 내용", null, null);

                        Toast.makeText(getApplicationContext(),
                                "문자 바로 보내기 완료!", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}
