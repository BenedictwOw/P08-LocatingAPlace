package com.myapplicationdev.android.p08_locatingaplace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    LatLng poi_Singapore,poi_east,poi_central,poi_north;
    Marker singapore,east,central,north;
    Button btn1, btn2, btn3;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                UiSettings ui = map.getUiSettings();


                ui.setZoomControlsEnabled(true);
                ui.setCompassEnabled(true);
                poi_Singapore = new LatLng(1.3521,103.8198);
                poi_central = new LatLng(1.300542, 103.841226);
                poi_east = new LatLng(1.350057,103.934452);
                poi_north = new LatLng(1.461708,103.813500);
                north = map.addMarker(new
                        MarkerOptions()
                        .position(poi_north)
                        .title("North - HQ")
                        .snippet("Block 333, Admiralty Ave 3, 765654 Operating hours: 10am-5pm\n" +
                                "Tel:65433456\n")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
                central = map.addMarker(new
                        MarkerOptions()
                        .position(poi_central)
                        .title("North - HQ")
                        .snippet("Block 3A, Orchard Ave 3, 134542 \n" +
                                "Operating hours: 11am-8pm\n" +
                                "Tel:67788652\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                east = map.addMarker(new
                        MarkerOptions()
                        .position(poi_east)
                        .title("East - HQ")
                        .snippet("Block 555, Tampines Ave 3, 287788 \n" +
                                "Operating hours: 9am-5pm\n" +
                                "Tel:66776677\"\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));







                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        if (marker.equals(north)){
                            Toast.makeText(MainActivity.this,"HQ - North", Toast.LENGTH_SHORT).show();
                        } else if(marker.equals(central)){
                            Toast.makeText(MainActivity.this,"HQ - Central", Toast.LENGTH_SHORT).show();
                        } else if (marker.equals(east)){
                            Toast.makeText(MainActivity.this,"HQ - East", Toast.LENGTH_SHORT).show();
                        } else {
                        }
                        return true;
                    }
                });


                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Singapore,
                        11));
                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }
            }

        });


        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    LatLng poi_north = new LatLng(1.461708, 103.813500);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_north,
                            15));
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    LatLng poi_central = new LatLng(1.300541, 103.841226);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_central,
                            15));
                }

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    LatLng poi_east = new LatLng(1.350057, 103.934452);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_east,
                            15));
                }
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
