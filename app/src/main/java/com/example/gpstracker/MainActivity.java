package com.example.gpstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.widget.TextView;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.Timer;
import java.util.TimerTask;

import javax.crypto.spec.GCMParameterSpec;

import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {
    private RxPermissions permissions;
    TextView locationText;
    GPSClass gpsClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openPermission();
        gpsClass = new GPSClass(this);


        setContentView(R.layout.activity_main);
        locationText = findViewById(R.id.location);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                String location = "\nLatitude: \n" + gpsClass.getLatitude() + "\nLongitude: \n" + gpsClass.getLongitude() + "\nCounter: " + gpsClass.getCounter();

                runOnUiThread(() -> {
                    locationText.setText(location);
                });


                //Log.d(TAG, "onLocationChanged  " + location);
            }
        }, 0, 300);


    }
    private void openPermission() {
        permissions = new RxPermissions(this);
        permissions.requestEach(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION
                )
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {

                        } else if (permission.shouldShowRequestPermissionRationale) {

                        } else {

                        }
                    }
                });
    }
}