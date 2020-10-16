package com.example.p458;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.webView);  // webView 를 가져온다.
        webView.setWebViewClient(new WebViewClient());  // 안드로이드에서 제공해주는 기본적인 브라우저의 기능

        WebSettings webSettings = webView.getSettings();  // setting
        webSettings.setJavaScriptEnabled(true);  // JavaScript 허용
    }
    public void ckbt(View v){  //button ,button2, button3 을 클릭하면  webView 를 출력하자!
        if(v.getId()==R.id.button){
            webView.loadUrl("http://m.naver.com");
        }else if(v.getId()==R.id.button2){
            webView.loadUrl("http://www.daum.net");
        }else if(v.getId()==R.id.button3){
            webView.loadUrl("http://192.168.0.3/android");
        }
    }
}