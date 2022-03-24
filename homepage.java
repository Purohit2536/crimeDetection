package com.example.crimedetection;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;
import android.widget.SearchView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class HomePage extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;
    private SearchView btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        btn = findViewById(R.id.SearchBar);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        LatLng Gujarat = new LatLng(22.463822, 70.079729);
        map.addMarker(new MarkerOptions().position(Gujarat).title("Darbar Gadh"));
        map.moveCamera(CameraUpdateFactory.newLatLng(Gujarat));

        LatLng Guj = new LatLng(22.463309, 70.058649);
        map.addMarker(new MarkerOptions().position(Guj).title("Oshwal Colony "));
        map.moveCamera(CameraUpdateFactory.newLatLng(Guj));

        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);

    }
}
