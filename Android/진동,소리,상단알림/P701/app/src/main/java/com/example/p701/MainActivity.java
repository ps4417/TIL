package com.example.p701;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    NotificationManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void ck1(View v){ // 진동을 울려라~   => 이걸 나중에 신호가 왔을 때 진동이 일어나도록 만들 수 있다.
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if(Build.VERSION.SDK_INT>=26){
            vibrator.vibrate(VibrationEffect.createOneShot(1000,10));
        }else{
            vibrator.vibrate(1000);
        }

    }
    public void ck2(View v){ // 소리가 나도록 해라
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(),uri);
        ringtone.play();

    }


    public void ck3(View v){ // 지정한 음악이 들리도록 해라
        MediaPlayer player = MediaPlayer.create(getApplicationContext(),R.raw.mp);
        player.start();


    }
    public void ck4(View v){  // 상단에 알림 창 뜨도록
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = null;
        if(Build.VERSION.SDK_INT>=26){
            if(manager.getNotificationChannel("ch1")==null){
                manager.createNotificationChannel(new NotificationChannel("ch1","chname",NotificationManager.IMPORTANCE_DEFAULT));
                builder = new NotificationCompat.Builder(this,"ch1");
            }
        }else{
            builder = new NotificationCompat.Builder(this);
        }
        builder.setContentTitle("Noti Test");
        builder.setContentText("Content Text");
        builder.setSmallIcon(R.drawable.d2);
        Notification noti = builder.build();
        manager.notify(1,noti);
    }
    public void ck5(View v){
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = null;
        if(Build.VERSION.SDK_INT>=26){
            if(manager.getNotificationChannel("ch2")==null){
                manager.createNotificationChannel(new NotificationChannel("ch2","chname2",NotificationManager.IMPORTANCE_DEFAULT));
                builder = new NotificationCompat.Builder(this,"ch2");
            }
        }else{
            builder = new NotificationCompat.Builder(this);
        }
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,101,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setAutoCancel(true);  // notification 클릭하면 사라지도록
        builder.setContentIntent(pendingIntent);
        builder.setContentTitle("Noti Test");
        builder.setContentText("Content Text");
        builder.setSmallIcon(R.drawable.d2);
        Notification noti = builder.build();
        manager.notify(1,noti);
    }

}