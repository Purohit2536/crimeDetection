package com.example.crimeapp;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class
HomePage extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener {

    SupportMapFragment mapFragment;
    GoogleMap map;
    private SearchView Searchview;
    private Button btn;
    private Marker marker;

    ArrayList<LatLng>arrayList = new ArrayList<LatLng>();
    LatLng Delhi = new LatLng(28.706851, 77.156666);
    LatLng Guj = new LatLng(22.463309, 70.058649);
    LatLng Mumbai = new LatLng(19.083406, 72.882880);
    LatLng UP = new LatLng(27.680003, 79.555364);
    LatLng Kerala = new LatLng(22.358710, 82.750426);
    LatLng gj10 = new LatLng(22.457235, 70.086542);
    LatLng Korba = new LatLng(22.358710, 82.750426);
    LatLng Pihani_UP = new LatLng(27.620270, 80.201123);
    LatLng Lucknow = new LatLng(22.358710, 82.750426);
    LatLng Karur = new LatLng(22.358710, 82.750426);


    // another arraylist for the names of the marker
    ArrayList<String>title  = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        // we will add items to our arraylist
        arrayList.add(Delhi);
        arrayList.add(Guj);
        arrayList.add(Mumbai);
        arrayList.add(UP);
        arrayList.add(Kerala);
        arrayList.add(gj10);
        arrayList.add(Korba);
        arrayList.add(Pihani_UP);
        arrayList.add(Lucknow);
        arrayList.add(Karur);


        // now we will add title for marker in the arraylist
        title.add("Murder");
        title.add("Firing");
        title.add("Murder");
        title.add("Violence");
        title.add("Robbery");
        title.add("Robbery");
        title.add("Violence");
        title.add("Robbery");
        title.add("Firing");
        title.add("Murder");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {


            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuitem) {

                switch (menuitem.getItemId()){
                    case R.id.crime: startActivity(new Intent(getApplicationContext(),Crime_Activity.class));
                        overridePendingTransition(0,0);
                        return;

                    case R.id.home:
                        return;

                    case R.id.addimage: startActivity(new Intent(getApplicationContext(),ImageActivity.class));
                        overridePendingTransition(0,0);
                        return;
                }
            }
        });

        btn = findViewById(R.id.Emergencybtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HomePage.this, EmergencyContacts.class);
                startActivity(i);
            }
        });

        Searchview = findViewById(R.id.Searchview);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;

        Searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = Searchview.getQuery().toString();
                List<Address> addressList = null;

                Geocoder geocoder = new Geocoder(HomePage.this);
                try {
                    addressList = geocoder.getFromLocationName(location, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Address address = addressList.get(0);
                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        // make sure to add this line otherwise the app will crash
        map = googleMap;

        // this loop for adding markers
        for (int i=0; i<arrayList.size(); i++) {

            //this loop is for setting the title of the marker
            for (int j=0; j<title.size(); j++) {

                map.addMarker(new MarkerOptions().position(arrayList.get(i)).title(String.valueOf(title.get(i))));
            }
            map.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));
        }

        // click listener to marker

        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {

                String markertitle = marker.getTitle();
                Intent i = new Intent(HomePage.this, MurderActivity.class);
                i.putExtra("title",markertitle);
                startActivity(i);
                return true;
            }
        });

        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setScrollGesturesEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {

        Toast.makeText(this, "My Position"+ marker.getPosition(), Toast.LENGTH_SHORT).show();

        return false;
    }

    @Override
    public void onMarkerDrag(@NonNull Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(@NonNull Marker marker) {

    }

    @Override
    public void onMarkerDragStart(@NonNull Marker marker) {

        Toast.makeText(this, "Lat: " + marker.getPosition().latitude + "\nLng: "  +marker.getPosition().longitude, Toast.LENGTH_LONG).show();

    }

}
