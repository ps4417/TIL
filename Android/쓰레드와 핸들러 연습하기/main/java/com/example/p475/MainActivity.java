package com.example.p475;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    TextView textView2;
    Button button;
    Myhandler myHandler;
    Myhandler2 myHandler2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        button = findViewById(R.id.button);
        myHandler = new Myhandler();
        myHandler2 = new Myhandler2();
    }
    public void ckbt(View v){
        Thread t = new Thread(new MyThread());
        Thread t2 = new Thread(new MyThread2());
        t.start();
        t2.start();
        button.setEnabled(false);
    }





    class Myhandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            Bundle bundle = msg.getData();
            int data = bundle.getInt("tdata",0);
            textView.setText(data+"km");


        }
    }
    class Myhandler2 extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            Bundle bundle = msg.getData();
            int data = bundle.getInt("rdata",0);
            textView2.setText(data+"rpm");


        }
    }


    class MyThread implements Runnable{

        @Override
        public void run() {

            for(int i=0;i<=5000;i++){
                Random r = new Random();
                int tdata = r.nextInt(200)+1;
                Message message = myHandler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("tdata",tdata);
                message.setData(bundle);
                myHandler.sendMessage(message);

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class MyThread2 implements Runnable{

        @Override
        public void run() {

            for(int i=0;i<=5000;i++){
                Random r = new Random();
                int rdata = r.nextInt(5000)+1;
                Message message = myHandler2.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("rdata",rdata);
                message.setData(bundle);
                myHandler2.sendMessage(message);

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}