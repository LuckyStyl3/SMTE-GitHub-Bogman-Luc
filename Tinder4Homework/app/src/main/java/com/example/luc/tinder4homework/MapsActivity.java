package com.example.luc.tinder4homework;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity {

    private SharedPreferences pref;
    private String prefMyLocation = "MyLocation";
    private String MyLatitude = "mylatitude";
    private String MyLongitude = "mylongitude";

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        pref = getSharedPreferences(prefMyLocation, MODE_PRIVATE);

        LatLng Maastricht = new LatLng(50.851,5.690);
        LatLng LocationNu = new LatLng(pref.getFloat(MyLatitude,0), pref.getFloat(MyLongitude, 30));

        mMap.setMyLocationEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Maastricht, 13));

        mMap.addMarker(new MarkerOptions().position(Maastricht).title("Marker"));
        mMap.addMarker(new MarkerOptions().position(LocationNu).title("Marker"));

    }
}
