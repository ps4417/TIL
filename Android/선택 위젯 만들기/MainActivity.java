package com.example.p426;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> datas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);

    }

    public void setList(final ArrayList<String> datas){  //adapter를 만들어서 listView에 붙인다.
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datas);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
//                Toast.makeText(MainActivity.this, ""+position, Toast.LENGTH_SHORT).show();

                //대화상자 뜨게 만들자
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("HI");
                builder.setMessage("Do you want to delete this item?"+ datas.get(position));

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        datas.remove(position);  //데이터에서는 삭제됨 (but, 이것만으로는 화면에서 삭제 안된다.!)
                        adapter.notifyDataSetChanged();  //데이터가 바뀐걸 화면에서 다시 리프레시 해주게된다.
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //싫으면 아무것도 안하면 됨
                    }
                });

                builder.show();
            }
        });
    }

    public void getData(){
        datas = new ArrayList<>();
        for(int i=1;i<=20;i++){
            datas.add("Item:"+i);
        }
        setList(datas);
    }

    //화면에서 버튼을 클리하면 데이터를 뿌릴거다.
    public void ckbt(View v){
        getData();
    }
}