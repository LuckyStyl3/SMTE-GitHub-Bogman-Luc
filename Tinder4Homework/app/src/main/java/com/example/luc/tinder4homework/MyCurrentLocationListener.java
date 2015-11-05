package com.example.luc.tinder4homework;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MyCurrentLocationListener implements LocationListener {


    private Context context;
    private String prefMyLocation = "MyLocation";
    private String MyLatitude = "mylatitude";
    private String MyLongitude = "mylongitude";

    public void setContext(Context context){
        this.context = context;
    }


    @Override
    public void onLocationChanged(Location location) {
        location.getLongitude();
        location.getLatitude();

        //String myLocation = "Latitude = " + location.getLatitude() + " Longitude = " + location.getLongitude();

        //I make a log to see the results
        //Log.e("MY CURRENT LOCATION", myLocation);

        SharedPreferences pref = this.context.getSharedPreferences(
                prefMyLocation, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putFloat(MyLatitude, (float) location.getLatitude());
        editor.putFloat(MyLongitude, (float) location.getLongitude());

        editor.commit();
    }


    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }


}
