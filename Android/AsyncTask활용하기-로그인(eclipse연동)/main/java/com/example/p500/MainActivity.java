package com.example.p500;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tx_id,tx_pwd;
    HttpAsync httpAsync;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tx_id = findViewById(R.id.tx_id);
        tx_pwd = findViewById(R.id.tx_pwd);
    }
    public void ck(View v){
        String id = tx_id.getText().toString();
        String pwd = tx_pwd.getText().toString();
        String url = "http://192.168.0.3/android/login.jsp";
        url += "?id="+id+"&pwd="+pwd;
        httpAsync = new HttpAsync();
        httpAsync.execute(url);


    }

    class HttpAsync extends AsyncTask<String,String,String>{
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Login...");
//            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String url = strings[0].toString();
            String result = HttpConnect.getString(url);
            return result; // return 하면 post 로 간다.
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            progressDialog.dismiss();
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();

            String result = s.trim();
            if(result.equals("1")){
//                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                intent.putExtra("str","로그인하셨습니다.");
                startActivity(intent);
            }else{
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("LOGIN FAIL");
                dialog.setMessage("Try Again..");
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                dialog.show();
            }
        }


    }



}