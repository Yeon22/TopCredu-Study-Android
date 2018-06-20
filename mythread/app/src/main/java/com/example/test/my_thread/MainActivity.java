package com.example.test.my_thread;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Handler handler;
    private ListView lvAppList;
    private ArrayList<String> appListString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		handler = new Handler();
		lvAppList = (ListView) findViewById(R.id.lvAppList);
    }

    // 휴대폰에 설치된 앱정보를 구한다.
    public void onBtnGetApps(View view) {
        new GetAppList().start();
    }

    private class GetAppList extends Thread {
        @Override
        public void run() {
            PackageManager pm = getPackageManager();

            // 바탕화면에 아이콘이 노출된 앱
            Intent intent = new Intent(Intent.ACTION_MAIN, null);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);

            List<ResolveInfo> appList = pm.queryIntentActivities(intent, 0);
            Collections.sort(appList, new ResolveInfo.DisplayNameComparator(pm));

            appListString = new ArrayList<String>();
            int count = 0;
            // ResolveInfo 자료형의 객체는 취급하기 불편하므로
            // 해당 앱의 패키지 문자열만 뽑아서 새 리스트<String>에 옮겨담는 로직
            for (ResolveInfo r : appList) {
                count++;
                String appTitle = count + ":" + r.activityInfo.applicationInfo.packageName;
                appListString.add(appTitle);
            }

            handler.post(new Runnable() {
                @Override
                public void run() {
                    lvAppList.setAdapter(new ArrayAdapter<String>(
                            MainActivity.this,
                            android.R.layout.simple_list_item_single_choice, appListString));

                    // 아이템의 디자인을 라디오 버튼을 표시했다.
                    // 추가로 라디오 버튼의 선택 기능을 활성화 해야 한다.
                    lvAppList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                }
            });
        }
    }

    public void onBtnCallApp(View view) {
        new Thread(new CallApp()).start();
    }

    private class CallApp implements Runnable {
        @Override
        public void run() {
            String item = appListString.get(lvAppList.getCheckedItemPosition());
            debug(item);

            Intent intent = getPackageManager().getLaunchIntentForPackage(item.split(":")[1]);
            startActivity(intent);
        }
    }

    public void debug(final String data) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Log.d("xxx", "" + data);
            }
        });
    }
}
