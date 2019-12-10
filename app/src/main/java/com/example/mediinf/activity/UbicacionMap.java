package com.example.mediinf.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mediinf.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class UbicacionMap extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final int PERMISSIONS_REQUEST = 100;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion_map);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                initMap();
            }
        });
    }

    private void initMap(){
        Log.d(TAG, "initMap");
        if(ContextCompat.checkSelfPermission(UbicacionMap.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(UbicacionMap.this, new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST);
            return;        }

        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                Toast.makeText(UbicacionMap.this, "My Ubicación", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);// Controles de zoom
        uiSettings.setCompassEnabled(true); // Brújula
         uiSettings.setMyLocationButtonEnabled(true);    // Show MyLocationButton
        // Set OnMapClickListener
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Toast.makeText(UbicacionMap.this, "onMapClick: " + latLng, Toast.LENGTH_LONG).show();
            }
        });

        // Set OnMapLongClickListener
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                Toast.makeText(UbicacionMap.this, "onMapLongClick: " + latLng, Toast.LENGTH_LONG).show();
            }
        });



        LatLng latLng = new LatLng(-12.038154, -76.965504);
        Log.d(TAG, "LatLng: " + latLng);

        MarkerOptions markerOptions = new MarkerOptions().position(latLng)
                .title("Botica Mifarma")
                .snippet("Farmacia en el distrito de Santa Anita, Perú")
                .snippet("Ubicación: Av. Los Chancas 486, Santa Anita 15009");
        Marker marker = mMap.addMarker(markerOptions);



        LatLng latLng2 = new LatLng( -12.054222, -76.964354);
        Log.d(TAG, "LatLng: " + latLng2);

        MarkerOptions markerOptions2 = new MarkerOptions().position(latLng2)
                .title("Boticas Perú ")
                .snippet("Farmacia y artículos varios en el distrito de Santa Anita, Perú")
                .snippet("Ubicación: Calle Las Alondras 127, Lima");
        Marker marker2 = mMap.addMarker(markerOptions2);


        LatLng latLng3 = new LatLng( -12.051867, -76.958505);
        Log.d(TAG, "LatLng: " + latLng3);

        MarkerOptions markerOptions3 = new MarkerOptions().position(latLng3)
                .title("Boticas San Martín ")
                .snippet("Farmacia en el distrito de Santa Anita, Perú")
                .snippet("Ubicación: Las Alondras 375, Santa Anita 15009");
        Marker marker3 = mMap.addMarker(markerOptions3);




        marker.showInfoWindow();
        marker2.showInfoWindow();
        marker3.showInfoWindow();

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }
            @Override
            public View getInfoContents(Marker marker) {
                View view = getLayoutInflater().inflate(R.layout.info_content, null);
                ((ImageView) view.findViewById(R.id.icon)).setImageResource(R.mipmap.ic_launcher_round);
                TextView titleText = view.findViewById(R.id.title);
                titleText.setText(marker.getTitle());
                TextView snippetText = view.findViewById(R.id.snippet);
                snippetText.setText(marker.getSnippet());
                return view;
            }
        });


        // Set OnInfoWindowClickListener
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Toast.makeText(UbicacionMap.this, "onInfoWindowClick: "
                        + marker.getTitle() + "\n" +
                        marker.getPosition(), Toast.LENGTH_LONG).show();
            }
        });
        // Set OnMarkerClickListener
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(UbicacionMap.this, "onMarkerClick: "
                        + marker.getTitle() + "\n" +
                        marker.getPosition(), Toast.LENGTH_LONG).show();
                return false;
            }
        });
        // Draggable
        marker.setDraggable(true);
        // Presionar el marcador por unos segundos para activar 'drag'
        //Set OnMarkerDragListener
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {
                Log.d(TAG, "onMarkerDragStart: " + marker.getTitle());
            }
            @Override
            public void onMarkerDrag(Marker marker) {
                Log.d(TAG, "onMarkerDrag: " + marker.getTitle());
            }
            @Override
            public void onMarkerDragEnd(Marker marker) {
                Log.d(TAG, "onMarkerDragEnd: " + marker.getTitle());
                Toast.makeText(UbicacionMap.this, "onMarkerDragEnd: "
                        + marker.getTitle() + "\n" +
                        marker.getPosition(), Toast.LENGTH_LONG).show();
            }
        });


        // Set current position camera
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));
        CameraPosition camera = new CameraPosition.Builder()
                .target(latLng)
                .zoom(15)   // 1 - 21
                .bearing(180) // Giro: 0° - 360°
                .tilt(45)   // Inclinación: 0° - 90°
                .build();



    try {
        List<Address> addresses = new Geocoder(this).getFromLocation(latLng.latitude, latLng.longitude, 1);
        if (addresses != null && addresses.size() > 0) {
            Address address = addresses.get(0); // First address from position
            String city = address.getLocality();
            String state = address.getAdminArea();
            String country = address.getCountryName();
            String postalCode = address.getPostalCode();
            String fulladdress = address.getAddressLine(0);
            Toast.makeText(this, fulladdress, Toast.LENGTH_LONG).show();
        }
    }catch (IOException e){
        Log.e(TAG, e.getMessage(), e);
    }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults){
        if(requestCode == PERMISSIONS_REQUEST) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Debe conceder todos los permisos", Toast.LENGTH_LONG).show();
                    return;
                }
            }
            initMap();
        }
    }
    private LocationListener locationListener;
    private boolean locationUpdating = false;
    public void myLocation(View view) {
        Log.d(TAG, "myLocation");
        // Verify permissions
        if (ContextCompat.checkSelfPermission(UbicacionMap.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(UbicacionMap.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST);
            return;
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null) {
            // Verify GPS enabled
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                new AlertDialog.Builder(this)
                        .setMessage("Para verificar su ubicación se requiere activar el GPS.")
                        .setPositiveButton("Habilitar GPS", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                            }
                        }).create().show();
                return;
            }


        }

        if(!locationUpdating){
            Toast.makeText(this, "Start LocationUpdates", Toast.LENGTH_SHORT).show();
            // Listener to location status change
            locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    Log.d(TAG, "onLocationChanged by " + location.getProvider());
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    Log.d(TAG, "LatLng: " + latLng);
                    Toast.makeText(UbicacionMap.this, "latLng: " + latLng, Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                }
                @Override
                public void onProviderEnabled(String provider) {
                }
                @Override
                public void onProviderDisabled(String provider) {
                }
            };
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
            //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, locationListener);
            // Alternative
            locationUpdating = true;
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                // Change FAB color
                view.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorAccent)));
        }else{
            Toast.makeText(this, "Stop LocationUpdates", Toast.LENGTH_SHORT).show();
            locationManager.removeUpdates(locationListener);
            // Remove All location updates
            locationUpdating = false;
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                view.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, android.R.color.darker_gray)));
        }

        }
}
