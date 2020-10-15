package com.example.p362;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

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
                Manifest.permission.SEND_SMS,
                Manifest.permission.RECEIVE_SMS
        };
        ActivityCompat.requestPermissions(this,permissions,101);

        //intentFilter 등록
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");  // 네트워크 연결상태
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");  // 문자메시지

        //받는다
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {  //앱과 앱 사이는 intent로 받는다.
                String cmd = intent.getAction();
                ConnectivityManager cm = null;
                NetworkInfo mobile = null;
                NetworkInfo wifi = null;

                if(cmd.equals("android.net.conn.CONNECTIVITY_CHANGE")){ // 네트워크가 변경되었다는 정보이면 처리하겠다(브로드캐스트 정보 확인)
                    cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE); //CONNECTIVITY 네트워크를 가져올게
                    mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);  // ConnectivityManager로 끄집어냄
                    wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

                    if(mobile != null && mobile.isConnected()){

                    }else if(wifi != null && wifi.isConnected()){  //wifi 가 연결되었다면 wifi 사진 보여줘!
                        imageView.setImageResource(R.drawable.wifi);
                    }else{  //네트워크가 끊어진 상태를 의미한다.
                        imageView.setImageResource(R.drawable.nowifi);
                    }
                }else if(cmd.equals("android.provider.Telephony.SMS_RECEIVED")){ // 문자메시지 온걸 브로드캐스트 리시버가  보냈다면
                    Toast.makeText(context, "SMS_RECEIVED", Toast.LENGTH_SHORT).show();
                    Bundle bundle = intent.getExtras();
                    Object[] obj = (Object[]) bundle.get("pdus");  //pdus는 꼭 기억해
                    SmsMessage [] messages = new SmsMessage[obj.length];
                    for(int i =0;i<obj.length;i++){
                        String format = bundle.getString("format");
                        messages[i] = SmsMessage.createFromPdu((byte[])obj[i], format);  //obj[i]를 format 값으로 집어 넣겠다.
                    }
                    String msg = "";
                    if(messages != null && messages.length>0){
                        msg += messages[0].getOriginatingAddress()+"\n";
                        msg += messages[0].getMessageBody().toLowerCase()+"\n";
                        msg += new Date(messages[0].getTimestampMillis()).toString();
                        textView.setText(msg);
                    }
                }

            }
        }; // end BroadcastReceiver
        registerReceiver(broadcastReceiver,intentFilter); //가동

    }// end onCreate

    // unregister 해줘야한다.(앱이 끝나면 더이상 안받을거야!!)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    public void ckbt(View v){
        if(v.getId()==R.id.button){  //button 을 누르면 전화를 걸도록 해준다.
            int check = PermissionChecker.checkSelfPermission(this,Manifest.permission.CALL_PHONE);
            if(check == PackageManager.PERMISSION_GRANTED){  //permission 체크하고 Grant(승인) 하면 실행되도록 해준다.
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:010-1234-5678"));
                startActivity(intent);
            }else{  // deny되면 Toast 날려주자.
                Toast.makeText(this, "DENIED", Toast.LENGTH_SHORT).show();
            }
        }else if(v.getId()==R.id.button2){ //button2 누르면 문자보내기  //문자도 위처럼 permission_granted 해줘야 한다.
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("tel:010-1234-5678",null,"hi, bro...",null,null);
            Toast.makeText(this, "Send..OK", Toast.LENGTH_SHORT).show();
        }
    }
}