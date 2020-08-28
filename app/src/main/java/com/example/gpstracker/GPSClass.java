package com.example.gpstracker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaCodecInfo;
import android.os.Bundle;
import android.util.Log;

class GPSClass implements LocationListener {
    private LocationManager mLocationManager;
    private String TAG = "GPSClass";

    public  double latitude;
    public  double longitude;
    public  int counter = 0;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getCounter() {
        return counter;
    }

    @SuppressLint("MissingPermission")
    public GPSClass(Context context) {
        // set location events
        mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        if (mLocationManager.getAllProviders().contains(LocationManager.NETWORK_PROVIDER)) {
            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 15, this);
        }

        if (mLocationManager.getAllProviders().contains(LocationManager.GPS_PROVIDER)) {
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 15, this);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged: ");
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        counter++;
        Log.d(TAG, "onLocationChanged:latitude " + latitude);
        Log.d(TAG, "onLocationChanged:longitude " + longitude);
        Log.d(TAG, "onLocationChanged:counter " + counter);

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d(TAG, "onStatusChanged " + status);

    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d(TAG, "onProviderEnabled " + provider);
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d(TAG, "onProviderDisabled " + provider);
    }
}
