package com.example.p667;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        // 접근 허용
        String[] permission = {
                Manifest.permission.ACCESS_FINE_LOCATION
        };
        // 허용 요청
        ActivityCompat.requestPermissions(this,permission,101);

        // Manifest.permission.ACCESS_FINE_LOCATION 권한이 Denied 되면 Toast 로 끝났다고 알리고 앱을 꺼버려라(finish())
        if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED){
            Toast.makeText(this, "Finish", Toast.LENGTH_SHORT).show();
            finish();
        }
        // MyLocation 이라는 LocationListener 인터페이스를 받아서 사용
        MyLocation myLocation = new MyLocation();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1,0,myLocation);
    }

    // 버튼 클릭시 위치정보 가져와서 화면에 찍는다.
    public void ck(View v){
//        startMyLocation();
    }

//    private void startMyLocation() {
//        Location location = null;
//        if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED){
//            Toast.makeText(this, "Finish", Toast.LENGTH_SHORT).show();
//            finish();
//        }
//        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//        if(location != null){
//            double lat = location.getLatitude();
//            double lon = location.getLongitude();
//            textView.setText(lat+" "+lon);
//        }
//
//    }
    // 조금만 이동해도 위치정보가 변하도록 세팅
    // 리스너는 요청을 안해도 변화를 확인해서 위치정보를 찍는다.
    class MyLocation implements LocationListener{

        @Override
        public void onLocationChanged(@NonNull Location location) {
            double lat = location.getLatitude();
            double lon = location.getLongitude();
            textView.setText(lat+" "+lon);
        }
    }

}