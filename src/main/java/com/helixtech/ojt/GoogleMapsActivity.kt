package com.helixtech.ojt



import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class GoogleMapsActivity : AppCompatActivity(), OnMapReadyCallback {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_maps)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.gmap_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val helix = LatLng(37.4789026, 127.0368164)
        googleMap.addMarker(MarkerOptions().position(helix).title("Marker in Helixtech!"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(helix, 16f))
    }


}
