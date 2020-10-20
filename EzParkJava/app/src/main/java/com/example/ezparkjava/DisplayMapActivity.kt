package com.example.ezparkjava

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.ezparkjava.model.Map
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

private const val TAG = "DisplayMapActivity"
class DisplayMapActivity : AppCompatActivity(), OnInfoWindowClickListener, OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var userMap: Map
    private val LOCATION_PERMISSION_REQUEST = 1




    private fun getLocationAccess(){
        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            mMap.isMyLocationEnabled = true
        }
        else
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),LOCATION_PERMISSION_REQUEST)
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == LOCATION_PERMISSION_REQUEST){
            if(grantResults.contains(PackageManager.PERMISSION_GRANTED)){
                mMap.isMyLocationEnabled = true
            }
            else{
                Toast.makeText(this, "User has not granted location access permission", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_map)
        userMap = intent.getSerializableExtra(USER_MAP) as Map
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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
    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        getLocationAccess()

        Log.i(TAG,"map to render: ${userMap.title}")

        for (place in userMap.places){
            val LatLng = LatLng(place.latitude,place.longtitude)
//            mMap.addMarker(MarkerOptions().position(LatLng).title(place.title))
            mMap.addMarker(MarkerOptions().position(LatLng).title(place.title).snippet(place.details))

        }

        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val criteria = Criteria()

        val location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false)!!)
        if (location != null) {
            mMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude, location.longitude), 16f)
            )
        }
        mMap.setOnInfoWindowClickListener(this)

        //Set Custom InfoWindow Adapter
        val adapter : CustomInfoWindowAdapter = CustomInfoWindowAdapter(this@DisplayMapActivity)
        mMap.setInfoWindowAdapter(adapter)

    }

    override fun onInfoWindowClick(marker: Marker) {
//        Toast.makeText(
//            this, "Info window clicked",
//            Toast.LENGTH_SHORT
//        ).show()



        Log.i(TAG,"marker: ${marker.position}")
        for (place in userMap.places){
            if (place.title == marker.title) {
                Log.i(TAG,"title: ${place.title}")
            }
        }
    }

}