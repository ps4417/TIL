package com.example.tcpip;

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
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.msg.Msg;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    TextView tx_list,tx_msg;
    EditText et_ip,et_msg;

    int port;
    String address;
    String id;
    Socket socket;
    Sender sender;

    NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tx_list = findViewById(R.id.tx_list);
        tx_msg = findViewById(R.id.tx_msg);
        et_ip = findViewById(R.id.et_ip);
        et_msg = findViewById(R.id.et_msg);
        port = 5555;
        address = "192.168.0.3";
        id = "[CometPhone]";
        new Thread(con).start();


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

    }

    // 아까 보낸거 얘가 받아(1)
    public BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent != null){
                String title = intent.getStringExtra("title");
                String control = intent.getStringExtra("control");
                String data = intent.getStringExtra("data");

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
                builder.setContentTitle("Iot Test");
                builder.setContentText("Content Text");
                builder.setSmallIcon(R.drawable.down);
                Notification noti = builder.build();
                manager.notify(1,noti);

            }
        }
    };




    // 뒤로가기 버튼 눌렀을 때
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try{
            Msg msg = new Msg(null,"q");
            sender.setMsg(msg);
            new Thread(sender).start();
            if(socket != null){
                socket.close();
            }
            finish();
            onDestroy();
        }catch (Exception e){

        }
    }


    Runnable con = new Runnable() {
        @Override
        public void run() {
            try {
                connect();
            }catch(IOException e){
                e.printStackTrace();
            }

        }
    };

    public void connect() throws IOException {
        try {
            socket = new Socket(address, port);
        } catch (Exception e) {
            while (true) {
                try {
                    Thread.sleep(2000);
                    socket = new Socket(address, port);
                    break;
                } catch (Exception e1) {
                    System.out.println("Retry ...");
                }
            }
        }
        System.out.println("Connected Server:" + address);

        sender = new Sender(socket);
        new Receiver(socket).start();
        getList();
    }

    private void getList() {
        // 사용자의 정보를 주시오~
        Msg msg = new Msg(null,"[CometPhone]","1");
        sender.setMsg(msg);
        new Thread(sender).start();

    }

    public void clickBt(View v){
        String txip = et_ip.getText().toString();
        String txmsg = et_msg.getText().toString();
        Msg msg = null;
        if(txip == null || txip.equals("")){
            msg = new Msg(id,txmsg);
        }else{
            ArrayList<String> ips = new ArrayList<>();
            ips.add(txip);
            msg = new Msg(ips,id,txmsg);
        }
        sender.setMsg(msg);
        new Thread(sender).start();
    }

    class Receiver extends Thread {
        Socket socket;
        ObjectInputStream oi;

        public Receiver(){}

        public Receiver(Socket socket) throws IOException {
            this.socket = socket;
            oi = new ObjectInputStream(socket.getInputStream());
        }
        @Override
        public void run() {
            while(oi != null) {
                Msg msg  = null;
                try {
                    msg = (Msg) oi.readObject();
                    if(msg.getMaps() != null) {
                        HashMap<String,Msg> hm = msg.getMaps();
                        Set<String> keys = hm.keySet();
                        for(final String k : keys) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    String tx = tx_list.getText().toString();
                                    tx_list.setText(tx+k+"\n");
                                }
                            });
                            System.out.println(k);
                        }
                        continue;
                    }
                    final Msg finalMsg = msg;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String tx = tx_msg.getText().toString();
                            tx_msg.setText(finalMsg.getId()+ finalMsg.getMsg()+"\n"+tx);
                        }
                    });
                    System.out.println(msg.getId()+msg.getMsg());
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.print("서버 연결이 끊어졌습니다.");
                    break;
                }
            }
            try {
                if(oi != null) {
                    oi.close();
                }
                if(socket != null) {
                    socket.close();
                }
            }catch(Exception e) {

            }
        }

    }

    class Sender implements Runnable {
        Socket socket;
        ObjectOutputStream oo;
        Msg msg;

        public Sender(Socket socket) throws IOException {
            this.socket = socket;
            oo = new ObjectOutputStream(socket.getOutputStream());
        }

        public void setMsg(Msg msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            if (oo != null) {
                try {
                    oo.writeObject(msg);
                } catch (IOException e) {
//					e.printStackTrace();
                    try {
                        if (socket != null) {
                            socket.close();
                        }


                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    try {
                        // 재접속
                        Thread.sleep(2000);
                        connect();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }

    }

}