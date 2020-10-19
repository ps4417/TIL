package com.example.p534;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonWriter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    ListView listView;
    LinearLayout container;
    ArrayList<Movie> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String str_result = bundle.getString("str","");
        Toast.makeText(this, str_result, Toast.LENGTH_SHORT).show();


        // 필요한 변수 선언
        listView = findViewById(R.id.listView);
        container = findViewById(R.id.container);
        list = new ArrayList<>();
        getData();
    }
    public void getData(){
        String date = "20201018";
        String url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=430156241533f1d058c603178cc3ca0e&targetDt="+date;
        MovieAsync movieAsync = new MovieAsync();
        movieAsync.execute(url);
    }

    class MovieAsync extends AsyncTask<String,Void,String>{
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(SecondActivity.this);
            progressDialog.setTitle("Get data ...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String url = strings[0].toString();
            String result = HttpConnect.getString(url);
            return result;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            progressDialog.dismiss();
            JSONObject jo = null;
            try {
                jo = new JSONObject(s);
                JSONObject jo1 = jo.getJSONObject("boxOfficeResult");
                JSONArray ja = jo1.getJSONArray("dailyBoxOfficeList");
                for(int i = 0;i<ja.length();i++){
                    JSONObject jo2 = ja.getJSONObject(i);
                    String rank = jo2.getString("rank");
                    String name = jo2.getString("movieNm");
                    String audi = jo2.getString("audiCnt");
                    String open = jo2.getString("openDt");
                    String audiAll = jo2.getString("audiAcc");
                    String scrn = jo2.getString("scrnCnt");
                    Movie movie = new Movie(rank, name, audi, open, audiAll, scrn);
                    list.add(movie);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            MovieAdapter movieAdapter = new MovieAdapter();
            listView.setAdapter(movieAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this);
                    builder.setTitle("상세 페이지");
                    builder.setMessage("박스오피스 순위:" +list.get(position).getRank()+
                            "위\n영화제목:" +list.get(position).getName()+
                            "\n관객수:" +list.get(position).getAudi()+
                            "명\n개봉일:" +list.get(position).getOpen()+
                            "\n누적관객수:" +list.get(position).getAudiAll()+
                            "명\n상영관수:"+list.get(position).getScrn()+"개");
                    builder.show();
                }
            });


        }
    }

    class MovieAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View movieView = null;
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            movieView = inflater.inflate(R.layout.movie,container,true);
            TextView tx_rank = movieView.findViewById(R.id.textView);
            TextView tx_name = movieView.findViewById(R.id.textView2);
            TextView tx_audi = movieView.findViewById(R.id.textView3);

            tx_rank.setText("순위:"+list.get(position).getRank()+"위");
            tx_name.setText(list.get(position).getName());
            tx_audi.setText("관객수"+list.get(position).getAudi() + " 명");

            return movieView;
        }
    }
}