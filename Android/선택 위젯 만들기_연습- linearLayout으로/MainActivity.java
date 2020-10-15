package com.example.p428;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Person> persons;
    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        container = findViewById(R.id.container);
    }
    // start adapter
    // 우리만의 adapter 를 만들어야한다.
    class PersonAdapter extends BaseAdapter{
        ArrayList<Person> datas;

        public PersonAdapter(ArrayList<Person> datas){
            this.datas = datas;
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.person,container,true);

            // 4개의 위젯을 가져온다.
            ImageView im = view.findViewById(R.id.imageView);
            TextView tx_id = view.findViewById(R.id.tx_id);
            TextView tx_name = view.findViewById(R.id.tx_name);
            TextView tx_age = view.findViewById(R.id.tx_age);

            Person p = datas.get(position);

            im.setImageResource(p.getImg());
            tx_id.setText(p.getId());
            tx_name.setText(p.getName());
            tx_age.setText(p.getAge()+"");  // + "" 를 꼭 써서 String 으로 만들어야한다.

            return view;
        }
    } //end adapter



    public void setList(ArrayList<Person> persons){
        PersonAdapter personAdapter = new PersonAdapter(persons);
        listView.setAdapter(personAdapter);
    }

    public void getData(){
        persons = new ArrayList<>();
        persons.add(new Person(R.drawable.p1,"0001","박민영",20));
        persons.add(new Person(R.drawable.p2,"0002","신세경",21));
        persons.add(new Person(R.drawable.p3,"0003","설현",22));
        persons.add(new Person(R.drawable.p4,"0004","거미",23));
        persons.add(new Person(R.drawable.p5,"0005","벤",24));
        persons.add(new Person(R.drawable.p6,"0006","유리",25));
        persons.add(new Person(R.drawable.p7,"0007","아이유",26));
        persons.add(new Person(R.drawable.p8,"0008","블루",27));
        persons.add(new Person(R.drawable.p9,"0009","펀치",28));
        setList(persons);  //데이터 가져온거 setList 에 뿌려라
    }

    public void ckbt(View v){
        getData();
    }

}