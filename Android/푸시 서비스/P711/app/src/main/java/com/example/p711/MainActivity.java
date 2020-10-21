package com.example.p711;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    TextView tx;
    NotificationManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tx = findViewById(R.id.tx);
        FirebaseMessaging.getInstance().subscribeToTopic("car").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                String msg = "FCM Complete ...";
                if(!task.isSuccessful()){
                    msg = "FCM Fail";
                }
                Log.d("[TAG]:",msg);
            }
        });
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
        lbm.registerReceiver(receiver,new IntentFilter("notification"));
    } // end onCreate


    // 아까 보낸거 얘가 받아(1)
    public BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent != null){
                String title = intent.getStringExtra("title");
                String control = intent.getStringExtra("control");
                String data = intent.getStringExtra("data");
                tx.setText(control+" "+data);
                Toast.makeText(context, title+" "+control+" "+data, Toast.LENGTH_SHORT).show();

                // 진동이 울리도록
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if(Build.VERSION.SDK_INT>=26){
                    vibrator.vibrate(VibrationEffect.createOneShot(1000,10));
                }else{
                    vibrator.vibrate(1000);
                }

                // 소리가 울리도록
//                Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//                Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(),uri);
//                ringtone.play();

                // 상단 알림창이 뜨도록
                manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                NotificationCompat.Builder builder = null;
                if(Build.VERSION.SDK_INT>=26){
                    if(manager.getNotificationChannel("ch2")==null){
                        manager.createNotificationChannel(new NotificationChannel("ch2","chname2",NotificationManager.IMPORTANCE_DEFAULT));
                    }
                    builder = new NotificationCompat.Builder(MainActivity.this,"ch2");
                }else{
                    builder = new NotificationCompat.Builder(MainActivity.this);
                }
                Intent intent2 = new Intent(MainActivity.this,MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,101,intent2,PendingIntent.FLAG_UPDATE_CURRENT);

                builder.setAutoCancel(true);  // notification 클릭하면 사라지도록
                builder.setContentIntent(pendingIntent);
                builder.setContentTitle("Noti Test");
                builder.setContentText("Content Text");
                builder.setSmallIcon(R.drawable.down);
                Notification noti = builder.build();
                manager.notify(1,noti);

            }
        }
    };


}