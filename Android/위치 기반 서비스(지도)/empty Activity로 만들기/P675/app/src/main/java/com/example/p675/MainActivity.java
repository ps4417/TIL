package com.example.p675;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    SupportMapFragment supportMapFragment;
    GoogleMap gmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                gmap = googleMap;
                LatLng latlng = new LatLng(37.503699, 126.762684);
                gmap.addMarker(new MarkerOptions().position(latlng).title("부천시청 사거리"));
                gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng,15));

            }
        });
    }
    // 마커를 사용해 위치 표시하기
    public void ck1(View v){
                LatLng latlng = new LatLng(37.500356, 127.027036);
                gmap.addMarker(new MarkerOptions().position(latlng).
                        title("강남역 롯데시네마").snippet("xxx").icon(BitmapDescriptorFactory.fromResource(R.drawable.mv01))

                );
                gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng,15));

            }

    public void ck2(View v){

                LatLng latlng = new LatLng(33.450692, 126.570700);
                gmap.addMarker(new MarkerOptions().position(latlng).title("카카오 제주도 본사"));
                gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng,15));

            }

}