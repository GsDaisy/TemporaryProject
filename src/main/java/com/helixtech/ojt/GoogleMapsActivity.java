package com.helixtech.ojt;



import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoogleMapsActivity extends AppCompatActivity implements OnMapReadyCallback {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.gmap_fragment);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng helix = new LatLng(37.4789026, 127.0368164);
        googleMap.addMarker(new MarkerOptions().position(helix).title("Marker in Helixtech!"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(helix, 16));
    }
}
