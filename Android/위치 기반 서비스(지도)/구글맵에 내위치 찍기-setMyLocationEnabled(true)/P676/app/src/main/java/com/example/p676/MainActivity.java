package com.example.p676;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {
    SupportMapFragment supportMapFragment;
    GoogleMap gmap;
    TextView textView;
    LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 접근 허용
        String[] permission = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION

        };
        // 허용 요청
        ActivityCompat.requestPermissions(this,permission,101);

        supportMapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                gmap = googleMap;
                // Manifest.permission.ACCESS_FINE_LOCATION 권한이 Denied 혹은  Manifest.permission.ACCESS_COARSE_LOCATION Denied 면 return; 해라
                if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED
                        || checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED){
                    return;
                }
                gmap.setMyLocationEnabled(true);  // 내 위치 찍는 것

                LatLng latLng = new LatLng(37.507830, 126.932639);
                gmap.addMarker(new MarkerOptions().position(latLng).title("초기화면").snippet("xxx"));
                gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
            }
        });

        // --start Location
        textView = findViewById(R.id.textView);



        // MyLocation 이라는 LocationListener 인터페이스를 받아서 사용
        MyLocation myLocation = new MyLocation();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1,0,myLocation);
        // -- end Location


    }// end onCreate


    class MyLocation implements LocationListener {

        @Override
        public void onLocationChanged(@NonNull Location location) {
            double lat = location.getLatitude();
            double lon = location.getLongitude();
            textView.setText(lat+" "+lon);
            LatLng latLng = new LatLng(lat, lon);
//            gmap.addMarker(new MarkerOptions().position(latLng).title("My point").snippet("xxxx"));
            gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onResume() {  // 다시 시작할 때를 의미함
        super.onResume();
        if(gmap != null){

            gmap.setMyLocationEnabled(true);
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onPause() {
        super.onPause();
        if(gmap != null){

            gmap.setMyLocationEnabled(false);
        }
    }

}