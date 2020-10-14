package com.example.p351;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ActionBar actionBar;

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    Fragment4 fragment4;

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new Fragment1(this);
        fragment2 = new Fragment2(this);
        fragment3 = new Fragment3(this);
        fragment4 = new Fragment4();

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.framelayout,fragment1).commit();


        // 상단 메뉴 - 설정
        actionBar = getSupportActionBar();
        actionBar.setTitle("연습중");




        //하단 메뉴
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {  // toast 출력
                ProgressDialog progressDialog = null;
                if(item.getItemId()==R.id.tab1){
                    fragmentManager.beginTransaction().replace(R.id.framelayout,fragment1).commit();
                    Toast.makeText(MainActivity.this, "메뉴1", Toast.LENGTH_SHORT).show();
                }else if(item.getItemId()==R.id.tab2){  //AlertDialog 출력
                    fragmentManager.beginTransaction().replace(R.id.framelayout,fragment2).commit();
                    Toast.makeText(MainActivity.this, "메뉴2", Toast.LENGTH_SHORT).show();
//                    final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                    builder.setTitle("Menu2");
//                    builder.setMessage("메뉴2 화면입니다. 계속하시겠습니까?");
//
//                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//
//                        }
//                    });
//                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//
//                        }
//                    });
//
//                    AlertDialog dialog = builder.create();
//                    dialog.show();

                }else if(item.getItemId()==R.id.tab3){ //progress bar
                    fragmentManager.beginTransaction().replace(R.id.framelayout,fragment3).commit();
                    Toast.makeText(MainActivity.this, "메뉴3", Toast.LENGTH_SHORT).show();
//                    progressDialog = new ProgressDialog(MainActivity.this);
//                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//                    progressDialog.setTitle("메뉴3페이지 불러오는중...");
////                    progressDialog.setCancelable(false); // 이거쓰면 계속 돌아
//                    progressDialog.show();
                }
                return false;
            }
        });
    }

    // 상단메뉴 - 설정(그림)

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.setting){
            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, fragment4).commit();
            Toast.makeText(MainActivity.this, "Setting", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}