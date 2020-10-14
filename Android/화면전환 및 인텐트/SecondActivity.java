package com.example.p251;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int result = bundle.getInt("data",0);
        String str_result = bundle.getString("str","");
        Toast.makeText(this, str_result+":"+result, Toast.LENGTH_SHORT).show();
    }
    public void ckbt2(View v){
        Intent intent = new Intent(getApplicationContext(),ThirdActivity.class);
        intent.putExtra("data",200);
        intent.putExtra("str","String Data2");

        startActivity(intent);
    }
}