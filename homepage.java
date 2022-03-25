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
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class HomePage extends FragmentActivity implements OnMapReadyCallback {

    SupportMapFragment mapFragment;
    GoogleMap map;
    private SearchView Searchview;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

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
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
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
        map = googleMap;
        LatLng Gujarat = new LatLng(22.463822, 70.079729);
        map.addMarker(new MarkerOptions().position(Gujarat).title("Murder"));
        map.moveCamera(CameraUpdateFactory.newLatLng(Gujarat));

        LatLng Guj = new LatLng(22.463309, 70.058649);
        map.addMarker(new MarkerOptions().position(Guj).title("Firing"));
        map.moveCamera(CameraUpdateFactory.newLatLng(Guj));

        LatLng Jamnagar = new LatLng(22.447107, 70.049449);
        map.addMarker(new MarkerOptions().position(Jamnagar).title("Murder"));
        map.moveCamera(CameraUpdateFactory.newLatLng(Jamnagar));

        LatLng gj10 = new LatLng(22.457235, 70.086542);
        map.addMarker(new MarkerOptions().position(gj10).title("Vehicle Theft"));
        map.moveCamera(CameraUpdateFactory.newLatLng(gj10));

        LatLng Lalpur = new LatLng(22.440575, 70.072692);
        map.addMarker(new MarkerOptions().position(Lalpur).title("Violence"));
        map.moveCamera(CameraUpdateFactory.newLatLng(Lalpur));

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
}
