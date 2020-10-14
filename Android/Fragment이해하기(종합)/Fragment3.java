package com.example.p351;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Fragment3 extends Fragment {
    MainActivity m;
    Button button3;
    public Fragment3(MainActivity m) {
        this.m = m;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup viewGroup = null;
        viewGroup = (ViewGroup)inflater.inflate(R.layout.fragment_3,container,false);
        button3 = viewGroup.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog = null;
                progressDialog = new ProgressDialog(m);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setTitle("메뉴3페이지 불러오는중...");
//                progressDialog.setCancelable(false); // 이거쓰면 계속 돌아
                progressDialog.show();
            }
        });
        return viewGroup;
    }
}