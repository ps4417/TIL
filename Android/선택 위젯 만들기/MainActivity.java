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
    // 3. adapter 를 만들어서 listView 에 붙인다.
    public void setList(final ArrayList<String> datas){
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datas);  // simple_list_item_1은  android 에서 제공해주는 부분이므로 그냥 외울 것
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
//                Toast.makeText(MainActivity.this, ""+position, Toast.LENGTH_SHORT).show();  // 화면에 출력되는지 확인용도로 적어본다.

                // 4. 항목을 클릭했을 때 대화상자가 나오도록 만든다.(AlertDialog)
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("HI");
                builder.setMessage("Do you want to delete this item?"+ datas.get(position));

                // 5. YES 클릭시 항목이 삭제되도록 구현한다.
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        datas.remove(position);  //데이터에서는 삭제됨 (but, 이것만으로는 화면에서 삭제 안된다!)
                        adapter.notifyDataSetChanged();  //so, 데이터가 바뀐걸 변경해서 화면에 보여주는 nofifySetChanged() 메서드를 사용해야한다.
                    }
                });
                // 6. NO 클릭시 아무일도 안일어나도록 아무것도 적지 않는다.
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 따로 적을 필요가 없음
                    }
                });

                builder.show();
                // 아래처럼 적어도 보여지는건 같다!
                // AlertDialog dialog = builder.create();
                // dialog.show();
            }
        });
    }
    // 2. ArrayList<String> 객체를 생성하고 for문을 통해 20개를 담는다.
    // 2-1. setList() 메서드를 호출한다.
    public void getData(){
        datas = new ArrayList<>();
        for(int i=1;i<=20;i++){
            datas.add("Item:"+i);
        }
        setList(datas);
    }

    //화면에서 버튼을 클리하면 데이터를 뿌릴거다.
    // 1. 버튼을 클릭하면 getData() 메서드를 호출한다.
    public void ckbt(View v){
        getData();
    }
}