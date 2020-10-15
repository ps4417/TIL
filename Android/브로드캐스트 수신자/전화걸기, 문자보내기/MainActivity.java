package com.example.p362;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    TextView textView;
    //Button button;은 이벤트함수와 연결시킬거라서 안써도됨

    //BroadcastReceiver 선언
    BroadcastReceiver broadcastReceiver;
    //어떠한 브로드캐스트 종류를 받을 것인지 등록해놔야한다.(IntentFilter)
    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);

        //어떠한 퍼미션을 요청할 것인지 명시한다.
        String permissions []= {
                Manifest.permission.CALL_PHONE,
                Manifest.permission.SEND_SMS
        };
        ActivityCompat.requestPermissions(this,permissions,101);
    }



    public void ckbt(View v){
        if(v.getId()==R.id.button){  //button을 누르면 전화를 걸도록 해준다.
            int check = PermissionChecker.checkSelfPermission(this,Manifest.permission.CALL_PHONE);
            if(check == PackageManager.PERMISSION_GRANTED){  //permission체크하고 Grant(승인) 하면 실행되도록 해준다.
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:010-1234-5678"));
                startActivity(intent);
            }else{  // deny되면 Toast 날려주자.
                Toast.makeText(this, "DENIED", Toast.LENGTH_SHORT).show();
            }
        }else if(v.getId()==R.id.button2){ //button2 누르면 문자보내기
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("tel:010-1234-5678",null,"hi, bro...",null,null);
            Toast.makeText(this, "Send..OK", Toast.LENGTH_SHORT).show();
        }
    }
}