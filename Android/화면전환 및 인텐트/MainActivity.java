package com.example.p251;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String [] permissions = {
                Manifest.permission.CALL_PHONE
        };
        ActivityCompat.requestPermissions(this,permissions,101); // 바로 위에 배열 안에 있는 내용을 요청한다.

    }
    public void ckbt(View v) {
        Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
        intent.putExtra("data",100);
        intent.putExtra("str","String Data");

        startActivity(intent); //다음 액티비티로 이동한다.
    }
}