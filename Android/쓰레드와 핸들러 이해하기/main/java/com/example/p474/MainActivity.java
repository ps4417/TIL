package com.example.p474;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar,progressBar2;
    TextView textView,textView2;
    Button button,button2,button3;
    MyHandler myHandler;
    MyHandler2 myHandler2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        progressBar2 = findViewById(R.id.progressBar2);
        progressBar.setMax(50);
        progressBar2.setMax(50);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        //핸들러 선언 후 객체 생성
        myHandler = new MyHandler();
        myHandler2 = new MyHandler2();
    }
    public void ckbt(View v) throws InterruptedException {
        if(v.getId()==R.id.button){
//            for(int i=0;i<=30;i++){
//                progressBar.setProgress(i);
////                textView.setText(i+"");
//                Thread.sleep(1000);
//            }
//            t.start();  //쓰레드 만들고 스타트~

            // class 호출해서 사용
            MyThread t = new MyThread();
            t.start();
            button.setEnabled(false);  // 클릭해서 진행중일때는 재클릭을 못하도록 비활성화 시킨다.
        }else if(v.getId()==R.id.button2){
            //implement 받은건 다음과 같이 사용해줘야한다.
            Thread t = new Thread(new MyThread2());
            t.start();
            button2.setEnabled(false);
        }else if(v.getId()==R.id.button3){
            progress();
        }
    }

    public void progress(){
        final ProgressDialog progressDialog = new ProgressDialog(this);

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("progress");
        dialog.setMessage("5 seconds");
        final Handler handler = new Handler();

        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                progressDialog.setCancelable(false);
                progressDialog.setTitle("Downloading...");
                progressDialog.show();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                },5000);
            }
        });
        dialog.show();
    }





    // 쓰레드를 사용하는 방법 1
//    Thread t = new Thread(){};

    // 쓰레드를 사용하는 법 2
    //class 를 만들어서 사용하는 법 (상속)
    class MyThread extends Thread{
        @Override
        public void run() {
            for(int i=0;i<=50;i++){
                progressBar.setProgress(i);
                textView.setText(i+"");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // sub 쓰레드에서는 main 쓰레드의 특정 위젯을 변경할 수 없다. 때문에 그냥 쓰면 오류가 생긴다.
            // 아래와 같이 사용해주면 된다.
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    button.setEnabled(true);
                }
            });


        }
    };

    //----------------------------------------------------------------------
    // 핸들러: 서브쓰레드 안에서 동작되어지는 변경된 상태를 메인쓰레드에게 알려주는 역할
    class  MyHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            Bundle bundle = msg.getData();
            int data = bundle.getInt("tdata",0);
            textView.setText("Handler1:"+data);
            progressBar.setProgress(data);
        }
    }
    class  MyHandler2 extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            Bundle bundle = msg.getData();
            int data = bundle.getInt("tdata",0);
            textView2.setText("Handler2:"+data);
            progressBar2.setProgress(data);

            // 여기는 main 쓰레드 영역이므로 여기서 써도 된다.
            if(data == 49){
                button2.setEnabled(true);
            }
        }
    }
    // 쓰레드를 사용하는 법 3
    //  인터페이스를 통해서 쓰레드 만들어서 사용하기(with 핸들러)
    class MyThread2 implements Runnable{

        @Override
        public void run() {
            for(int i=1;i<50;i++){

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = myHandler.obtainMessage();
                Message message2 = myHandler2.obtainMessage();

                //번들 객체 만들어서 그 안에 값을 넣는다.
                Bundle bundle = new Bundle();
                bundle.putInt("tdata",i);

                //이를 다시 메시지에 넣는다.
                message.setData(bundle);
                message2.setData(bundle);

                //핸들러로 메시지 객체 보내기
                //myHandler.sendMessage(message);
                myHandler2.sendMessage(message2);

            }
        }
    }

}