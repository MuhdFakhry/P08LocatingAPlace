package sg.edu.rp.c346.p08_locatingaplace;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

public class MainActivity extends AppCompatActivity {

    Spinner spinnerArea;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        final LatLng poi_SG = new LatLng(1.352083, 103.819839);
        final LatLng poi_north = new LatLng(1.42976, 103.7800195);
        final LatLng poi_central = new LatLng(1.2976615, 103.8474856);
        final LatLng poi_east = new LatLng(1.3681007, 103.9519474);

        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                UiSettings ui = map.getUiSettings();
                ui.setCompassEnabled(true);
                UiSettings uis = map.getUiSettings();
                uis.setZoomControlsEnabled(true);



                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }

                //LatLng poi_SG = new LatLng(1.352083, 103.819839);

                //LatLng poi_north = new LatLng(1.42976, 103.7800195);
                Marker north = map.addMarker(new MarkerOptions()
                        .position(poi_north)
                        .title("North")
                        .snippet("Block 333, Admiralty Ave 3, 765654\n" + "Operating hours: 10am-5pm\n" +
                                "Tel:65433456\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

                //LatLng poi_central = new LatLng(1.2976615, 103.8474856);
                Marker central = map.addMarker(new MarkerOptions()
                        .position(poi_central)
                        .title("Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542 \n" + "Operating hours: 9am-5pm\n" +
                                "Tel:66776677\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                //LatLng poi_east = new LatLng(1.3681007, 103.9519474);
                Marker east = map.addMarker(new MarkerOptions()
                        .position(poi_east)
                        .title("East")
                        .snippet("Block 555, Tampines Ave 3, 287788 \n" + "Operating hours: 10am-5pm\n" +
                                "Tel:65433456\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));



                //map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_SG,11));

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Toast.makeText(getBaseContext(), marker.getTitle(), Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });
            }

        });

        spinnerArea = findViewById(R.id.spinnerArea);

        spinnerArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        if (map != null){
                            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_SG, 11));
                        }
                        break;
                    case 1:
                        if (map != null){
                            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_north, 15));
                        }
                        break;
                    case 2:
                        if (map != null){
                            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_central, 15));
                        }
                        break;
                    case 3:
                        if (map != null){
                            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_east, 15));
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        /*
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_north,15));
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_central,15));
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_east,15));
                }
            }
        });
        */
    }
}
