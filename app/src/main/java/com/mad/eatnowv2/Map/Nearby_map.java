package com.mad.eatnowv2.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.mad.eatnowv2.R;

public class Nearby_map extends AppCompatActivity {
    Spinner sp_type;
    Button btn_search;
    SupportMapFragment supportMapFragment;
    GoogleMap googleMap;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_map);
        //Assign variable
        sp_type = findViewById(R.id.sp_type);
        btn_search = findViewById(R.id.btn_find);
        //Initialize map fragment
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);
        //Initialize array place type
        String[] placeTypeList = {"atm", "bank", "convenience store", "restaurant"};

        //Initialize array of place name
        String[] placeNameList = {"ATM", "Bank", "Convenience Store", "Restaurant"};

        //set adapter on spinner
        sp_type.setAdapter(new ArrayAdapter<>(Nearby_map.this,
                android.R.layout.simple_spinner_dropdown_item, placeNameList));

        //initialize fused location provider client
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        //check permission
        if (ActivityCompat.checkSelfPermission(activity_user_dashboard.this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            //when permission granted
            //call method
        }
    }
}