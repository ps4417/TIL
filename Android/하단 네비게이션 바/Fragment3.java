package com.example.p331_1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class Fragment3 extends Fragment {
    Button button3;
    MainActivity m;

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
                Toast.makeText(m, "ccc", Toast.LENGTH_SHORT).show();
            }
        });
        return viewGroup;
    }
}