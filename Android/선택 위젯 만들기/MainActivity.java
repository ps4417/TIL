package com.example.p427;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Person> persons;
    ConstraintLayout container;
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
        public int getCount() {  // 항목의 개수를 의미
            return datas.size();
        }

        @Override
        public Object getItem(int position) {  // 항목의 위치를 의미
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {   // n번째 화면을 줄래
            View view = null;    // 여기서 view 는 한 셀을 의미한다.
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.person,container,true); //person.xml의 constraintLayout 의 이름을 container 라고 지정해줬음

            // 3개의 위젯을 가져온다.
            ImageView im = view.findViewById(R.id.imageView);
            TextView tx_name = view.findViewById(R.id.tx_name);
            TextView tx_phone = view.findViewById(R.id.tx_phone);

            Person p = datas.get(position);

            // 셋팅
            im.setImageResource(p.getId());
            tx_name.setText(p.getName());
            tx_phone.setText(p.getPhone());
            return view;
        }
    } //end adapter


    public void setList(ArrayList<Person> persons){
        PersonAdapter personAdapter = new PersonAdapter(persons);
        listView.setAdapter(personAdapter);
    }

    public void getData(){
        persons = new ArrayList<>();
        persons.add(new Person(R.drawable.p1,"박민영","010-1234-5678"));
        persons.add(new Person(R.drawable.p2,"신세경","010-2234-5678"));
        persons.add(new Person(R.drawable.p3,"설현","010-3234-5678"));
        persons.add(new Person(R.drawable.p4,"거미","010-4234-5678"));
        persons.add(new Person(R.drawable.p5,"벤","010-5234-5678"));
        persons.add(new Person(R.drawable.p6,"유리","010-6234-5678"));
        persons.add(new Person(R.drawable.p7,"송가인","010-7234-5678"));
        persons.add(new Person(R.drawable.p8,"블루","010-8234-5678"));
        persons.add(new Person(R.drawable.p9,"펀치","010-9234-5678"));
        setList(persons);  //데이터 가져온거 setList 에 뿌려라
    }

    public void ckbt(View v){
        getData();
    }
}