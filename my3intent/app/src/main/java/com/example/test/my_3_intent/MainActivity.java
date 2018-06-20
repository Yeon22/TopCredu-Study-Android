package com.example.test.my_3_intent;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView 메소드에 화면 디자인 XML을 전달하면
        // XML을 처리하여 화면을 구성한다.
        // res 폴더 밑에 있는 자원은 자동으로 R.java 파일에서 연동 id를 관리하므로
        // 파일을 직접 패스 문자열을 건네주는 대신
        // R.java 파일에서 관리하는 id 값을 전달하는 것으로 대신 처리한다.
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);

        // setContentView 메소드 처리 후라면 화면을 구성하는 객체가 모두 존재하므로
        // 원하는 뷰 객체의 참조를 획득하기 위해서 R.id.뷰아이디 방식으로
        // 해당 뷰의 참조를 findViewById 메소드로 얻을 수 있다.
        Button buttonDial = findViewById(R.id.buttonDial);

        // 이벤트를 거는 방법.
        buttonDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String title = ((Button) v).getText().toString();

                // 그냥 this로 설정하면 OnClickListener 객체를 지칭하므로 Context가 아니다.
                // 이 경우, 클래스명.this 방식으로 설정해서 해결할 수 있다.
//                Toast.makeText(MainActivity.this, title, Toast.LENGTH_LONG).show();

                buttonCallClick(v);
            }
        });
    }

    public void buttonCallClick(View view) {
        // 파라미터로 받는 view는 이벤트가 발생한 해당 뷰를 가리킨다.

        // getText() 메소드가 리턴하는 CharSequence 자료형을 toString 메소드로
        // 다운캐스팅하면 String 자료형을 얻을 수 있다.
        String title = ((Button) view).getText().toString();

        // this는 MainActivity 객체를 말한다.
        Toast.makeText(this, title, Toast.LENGTH_LONG).show();

        // 전화를 걸 수 있는 앱의 액티비티로 연동한다.
        String phone = editText.getText().toString();
        phone = "tel:" + phone; // tel: 이 문자는 전화번호다.
        switch (view.getId()) {
            case R.id.buttonCall:
                // 사용자의 OS 버전이 마시멜로우 이상인지 체크한다 .
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    /*
                     * 사용자 단말기의 권한 중 " 전화걸기 " 권한이 허용되어 있는지 확인한다 .
                     */
                    int permissionResult = checkSelfPermission(Manifest.permission.CALL_PHONE);
                    /*
                     * 패키지는 안드로이드 어플리케이션의 아이디이다 .
                     * 현재 어플리케이션이 CALL_PHONE 에 대해 거부되어있는지 확인한다 .
                     */
                    if (permissionResult == PackageManager.PERMISSION_DENIED) {
                        /*
                         * 사용자가 CALL_PHONE 권한을 거부한 적이 있는지 확인한다 .
                         * 거부 한적이 있으면 True 를 리턴하고
                         * 거부한적이 없으면 False 를 리턴한다 .
                         */
                        if (shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                            dialog.setTitle("권한이 필요합니다.").setMessage("이 기능을 사용하기 위해서는 단말기의 \"전화걸기\" 권한이 필요합니다. 계속 하시겠습니까?").setPositiveButton("네", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    /* 새로운 인스턴스 (onClickListener) 를 생성했기 때문에
                                     * 버전체크를 다시 해준다 .
                                     */
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        // CALL_PHONE 권한을 Android OS 에 요청한다 .
                                        requestPermissions(new
                                                String[]{
                                                Manifest.permission.CALL_PHONE
                                        }, 1000);
                                    }
                                }
                            }).setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(MainActivity.this, "기능을 취소했습니다", Toast.LENGTH_SHORT).show();
                                }
                            }).create().show();
                        }
                        // 최초로 권한을 요청할 때
                        else {
                            // CALL_PHONE 권한을 Android OS 에 요청한다
                            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1000);
                        }
                    }
                    // CALL_PHONE 의 권한이 있을 때
                    else {
                        startActivity(new Intent("android.intent.action.CALL", Uri.parse(phone)));
                    }
                }
                break;

            case R.id.buttonDial:
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse(phone)));
                break;
        }
    }

}
