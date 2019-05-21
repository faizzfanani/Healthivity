package com.kontrakanelite.healthivity.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import com.kontrakanelite.healthivity.R;
import com.kontrakanelite.healthivity.fragment.BottomSheetJoin;

import java.util.ArrayList;

public class DetailActivity extends FragmentActivity implements OnMapReadyCallback {
    LinearLayout layoutBottomJoin;
    Button btnJoin; TextView namaKomunitas, jadwalKomunitas;
    private ImageView back, ivKategori;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationCallback mLocationCallback;
    private static final int LOCATION_REQUEST = 500;
    ArrayList<LatLng> listPoints;

    private String kategori;
    private Double lat;
    private Double lon;
    float latitude, longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();

        btnJoin = (Button) findViewById(R.id.btnJoinDetail);
        layoutBottomJoin = findViewById(R.id.bottom_sheet_join);
        namaKomunitas = findViewById(R.id.tvNamaDetail);
        jadwalKomunitas = findViewById(R.id.tvDeskripsi);
        back = findViewById(R.id.ivBackDetail);
        ivKategori = findViewById(R.id.ivFotoDetail);

        namaKomunitas.setText(intent.getStringExtra("nama"));
        latitude = intent.getFloatExtra("latitude",0);
        longitude = intent.getFloatExtra("longitude",0);
        //latitude sama longitude ini diubah jadi address biasa, sama dikasih gmapsnya kalo bisa
        jadwalKomunitas.setText("Jadwal kumpul: \n"+intent.getStringExtra("jadwal"));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetJoin bottomSheetJoin = new BottomSheetJoin();
                bottomSheetJoin.show(getSupportFragmentManager(),"joinBottomSheet");
            }
        });
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        listPoints = new ArrayList<>();

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
    }

    private LocationRequest getLocationRequest() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return locationRequest;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);
            return;
        }

        mFusedLocationClient.requestLocationUpdates
                (getLocationRequest(),
                        mLocationCallback = new LocationCallback(),
                        null /* Looper */);

        mFusedLocationClient.getLastLocation().addOnSuccessListener(
                new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            lat = location.getLatitude();
                            lon = location.getLongitude();

                            MarkerOptions markerOptions1 = new MarkerOptions();
                            markerOptions1.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                            markerOptions1.title("Your Location");
                            markerOptions1.position(new LatLng(lat, lon));
                            mMap.addMarker(markerOptions1);
                        }
                    }
                });

        mMap.setMyLocationEnabled(true);
        //Marker Tempat kursus
        MarkerOptions markerOptions2 = new MarkerOptions();
        markerOptions2.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        markerOptions2.position(new LatLng((double)latitude, (double)longitude));
        markerOptions2.title("Place Name");
        mMap.addMarker(markerOptions2);
        float zoomLevel = 15.0f; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-7.9509977, 112.6152273), zoomLevel));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}