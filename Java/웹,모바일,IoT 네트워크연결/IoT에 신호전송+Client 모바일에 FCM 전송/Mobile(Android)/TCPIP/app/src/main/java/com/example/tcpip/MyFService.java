package com.example.tcpip;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFService extends FirebaseMessagingService {
    public MyFService() {

        // 이 클래스는 구글 클라우드 서버에서 보내오는 메시지를 받을 수 있으며, 메시지가 도착하면 onMessageReceived() 메서드가 자동으로 호출된다.
    }

    // 새로운 메시지를 받았을 때 호출되는 메서드 - 구글 클라우드 서버에서 보내오는 메시지를 받아서 처리할 수 있다.
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        String title = remoteMessage.getNotification().getTitle();
        String control = remoteMessage.getData().get("control");
        String data = remoteMessage.getData().get("data");
        Log.d("[TAG]:",title+" "+control+" "+data);


        // 보내버려 to MainActivity(1)
        Intent intent = new Intent("notification");  // 이 이름(notification)으로 mainActivity 에 보낼거야
        intent.putExtra("title",title);
        intent.putExtra("control",control);
        intent.putExtra("data",data);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

    }
}
