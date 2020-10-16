package com.example.p479;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button,button2;
    SeekBar seekBar;
    TextView textView;
    ImageView imageView;
    MyAsynch myAsynch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        // 초기 세팅 start버튼 활성화, stop 비활성화
        button.setEnabled(true);
        button2.setEnabled(false);

        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(100);
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);


    }
    public void ckbt1(View v){  // 쓰레드 동작
        myAsynch = new MyAsynch();
        myAsynch.execute(100);
    }
    public void ckbt2(View v){ // 쓰레드 멈춤
        myAsynch.cancel(true);
        myAsynch.onCancelled();
    }

    // asynch 사용
    class MyAsynch extends AsyncTask<Integer,Integer,String>{   //String 은 결과를 return 해주는 인자


        @Override
        protected void onPreExecute() {
            button.setEnabled(false);
            button2.setEnabled(true);
        }

        //쓰레드 영역(Run 부분)
        //쓰레드가 시작할 때 값을 넣어줄 수 있다.
        @Override
        protected String doInBackground(Integer... integers) {
            int a = integers[0].intValue();
            int sum = 0;
            for(int i=1;i<100;i++){
                if(isCancelled()==true){
                    break;
                }

                sum +=i;
                publishProgress(i);  //onProgressUpdate 로 계속 던진다.

                try {
                    Thread.sleep(100);   // 속도를 조절해 주는 함수
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "result: "+sum;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int i = values[0].intValue();
            seekBar.setProgress(i);
            if(i <=30){
                imageView.setImageResource(R.drawable.down);
            }else if(i <= 70){
                imageView.setImageResource(R.drawable.middle);
            }else if(i <=100){
                imageView.setImageResource(R.drawable.up);
            }
        }
        // 쓰레드가 끝났을때
        @Override
        protected void onPostExecute(String s) {
            textView.setText(s);
            button.setEnabled(true);
            button2.setEnabled(false);
        }
        // 쓰레드가 중간에 취소했을 때
        @Override
        protected void onCancelled() {
            seekBar.setProgress(0);
            textView.setText("");
            imageView.setImageResource(R.drawable.ic_launcher_background);
            button.setEnabled(true);
            button2.setEnabled(false);
        }
    }


}