package com.example.priyanka.mapsdemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
GoogleApiClient.OnConnectionFailedListener,
LocationListener{


    private GoogleMap mMap;
    private GoogleApiClient client;
    private LocationRequest locationRequest;
    private Location lastlocation;
    private Marker currentLocationmMarker;
    public static final int REQUEST_LOCATION_CODE = 99;
    int PROXIMITY_RADIUS = 10000;
    double latitude,longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            checkLocationPermission();

        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode)
        {
            case REQUEST_LOCATION_CODE:
                if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) !=  PackageManager.PERMISSION_GRANTED)
                    {
                        if(client == null)
                        {
                            bulidGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                }
                else
                {
                    Toast.makeText(this,"Permission Denied" , Toast.LENGTH_LONG).show();
                }
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Add a marker in Honolulu Hawaii and move the camera
        LatLng impacthub_hawaii = new LatLng(21.294907, -157.852051);
        mMap.addMarker(new MarkerOptions().position(impacthub_hawaii).title("Impact Hub Hawaii")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));


        //Locations for helpers

        LatLng oahu_church = new LatLng(35.302658, -120.664734);
        mMap.addMarker(new MarkerOptions().position(oahu_church).title("Shelter")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
        LatLng aloha_dog = new LatLng(35.302036, -120.662770);
        mMap.addMarker(new MarkerOptions().position(aloha_dog).title("Firefighter Bob")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        LatLng parking_lot = new LatLng(35.301327, -120.664905);
        mMap.addMarker(new MarkerOptions().position(parking_lot).title("Medical Aid")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
        LatLng chick = new LatLng(35.299584, -120.659891);
        mMap.addMarker(new MarkerOptions().position(chick).title("Firefighter Joe")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        LatLng oahu_church1 = new LatLng(35.296167, -120.659820);
        mMap.addMarker(new MarkerOptions().position(oahu_church1).title("Medical Aid")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));


        LatLng ward_theatre = new LatLng(21.294486, -157.853361);
        mMap.addMarker(new MarkerOptions().position(ward_theatre).title("Ward Theatre")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        LatLng modern_detail = new LatLng(21.295691, -157.851237);
        mMap.addMarker(new MarkerOptions().position(modern_detail).title("Modern Detail")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
        LatLng phuket_thai = new LatLng(21.294540, -157.851424);
        mMap.addMarker(new MarkerOptions().position(phuket_thai).title("Phuket Thai")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        LatLng prestige_valet = new LatLng(21.294364, -157.850797);
        mMap.addMarker(new MarkerOptions().position(prestige_valet).title("Prestige Valet")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        LatLng uhaul = new LatLng(21.295441, -157.851130);
        mMap.addMarker(new MarkerOptions().position(uhaul).title("U-Haul")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        LatLng tint_shop = new LatLng(21.295608, -157.852322);
        mMap.addMarker(new MarkerOptions().position(tint_shop).title("Tint Shop Hawaii")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
        LatLng bliss_day = new LatLng(21.295022, -157.850847);
        mMap.addMarker(new MarkerOptions().position(bliss_day).title("Bliss Day Spa")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        //Creating Circle


        Circle circle = googleMap.addCircle(new CircleOptions()
        .center(new LatLng(latitude, longitude))
        .radius(10000)
        .strokeColor(Color.CYAN)
        .fillColor(Color.MAGENTA));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(impacthub_hawaii));

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            bulidGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
    }


    protected synchronized void bulidGoogleApiClient() {
        client = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        client.connect();

    }

    @Override
    public void onLocationChanged(Location location) {

        latitude = location.getLatitude();
        longitude = location.getLongitude();
        lastlocation = location;
        if(currentLocationmMarker != null)
        {
            currentLocationmMarker.remove();

        }
        Log.d("lat = ",""+latitude);
        LatLng latLng = new LatLng(location.getLatitude() , location.getLongitude());
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(latLng);
//        markerOptions.title("Current Location");
//        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));//Original marker
//        currentLocationmMarker = mMap.addMarker(markerOptions);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 16.0f));
        Circle circle = mMap.addCircle(new CircleOptions()
                .center(new LatLng(latitude, longitude))
                .radius(350)
                .strokeColor(Color.RED)
                .fillColor(0x1ff40001));

//        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//        mMap.animateCamera(CameraUpdateFactory.zoomBy(16));

        if(client != null)
        {
            LocationServices.FusedLocationApi.removeLocationUpdates(client,this);
        }
    }

    public void onClick(View v)
    {
        Object dataTransfer[] = new Object[2];
        GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();

        switch(v.getId())
        {
            case R.id.B_search:
                EditText tf_location =  findViewById(R.id.TF_location);
                String location = tf_location.getText().toString();
                List<Address> addressList;


                if(!location.equals(""))
                {
                    Geocoder geocoder = new Geocoder(this);

                    try {
                        addressList = geocoder.getFromLocationName(location, 5);

                        if(addressList != null)
                        {
                            for(int i = 0;i<addressList.size();i++)
                            {
                                LatLng latLng = new LatLng(addressList.get(i).getLatitude() , addressList.get(i).getLongitude());
                                MarkerOptions markerOptions = new MarkerOptions();
                                markerOptions.position(latLng);
                                markerOptions.title(location);
                                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                                mMap.addMarker(markerOptions);
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                                mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.B_fire: //LOLOLOLOLOLOL
//                mMap.clear();
                String hospital = "hospital";
                String url = getUrl(latitude, longitude, hospital);
                dataTransfer[0] = mMap;
                dataTransfer[1] = url;
//
//                getNearbyPlacesData.execute(dataTransfer);

                Intent main_page = new Intent(MapsActivity.this, MainActivity.class);
                startActivity(main_page);
                Toast.makeText(MapsActivity.this, "Showing Favorite Parking", Toast.LENGTH_SHORT).show();
                break;


            case R.id.B_resources:
//                mMap.clear();
                String school = "school";
                url = getUrl(latitude, longitude, school);
                dataTransfer[0] = mMap;
                dataTransfer[1] = url;

//                getNearbyPlacesData.execute(dataTransfer);
                Intent main_page1 = new Intent(MapsActivity.this, MainActivity.class);
                startActivity(main_page1);
                Toast.makeText(MapsActivity.this, "Showing Recent Parking", Toast.LENGTH_SHORT).show();
                break;
            case R.id.B_evacuate:
//                mMap.clear();
                String resturant = "restuarant";
                url = getUrl(latitude, longitude, resturant);
                dataTransfer[0] = mMap;
                dataTransfer[1] = url;

//                getNearbyPlacesData.execute(dataTransfer);
                Intent main_page2 = new Intent(MapsActivity.this, MainActivity.class);
                startActivity(main_page2);
                Toast.makeText(MapsActivity.this, "BlueFire Menu", Toast.LENGTH_SHORT).show();
                break;
            case R.id.B_menu:
                    mMap.clear();
                String menu = "menu";
                url = getUrl(latitude, longitude, menu);
                dataTransfer[0] = mMap;
                dataTransfer[1] = url;

    //                getNearbyPlacesData.execute(dataTransfer);
                Intent main_page3 = new Intent(MapsActivity.this, MainActivity.class);
                startActivity(main_page3);
                Toast.makeText(MapsActivity.this, "BlueFire Menu", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    private String getUrl(double latitude , double longitude , String nearbyPlace)
    {

        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlaceUrl.append("location="+latitude+","+longitude);
        googlePlaceUrl.append("&radius="+PROXIMITY_RADIUS);
        googlePlaceUrl.append("&type="+nearbyPlace);
        googlePlaceUrl.append("&sensor=true");
        googlePlaceUrl.append("&key="+"AIzaSyBLEPBRfw7sMb73Mr88L91Jqh3tuE4mKsE");

        Log.d("MapsActivity", "url = "+googlePlaceUrl.toString());

        return googlePlaceUrl.toString();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        locationRequest = new LocationRequest();
        locationRequest.setInterval(100);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED)
        {
            LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, this);
        }
    }


    public boolean checkLocationPermission()
    {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)  != PackageManager.PERMISSION_GRANTED )
        {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION))
            {
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION },REQUEST_LOCATION_CODE);
            }
            else
            {
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION },REQUEST_LOCATION_CODE);
            }
            return false;

        }
        else
            return true;
    }


    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }
}
