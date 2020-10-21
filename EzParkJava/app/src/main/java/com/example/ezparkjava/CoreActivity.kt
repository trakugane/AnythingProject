package com.example.ezparkjava

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ezparkjava.model.Map
import com.example.ezparkjava.model.Place
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.navigation.NavigationView
//import kotlinx.android.synthetic.main.activity_favourite.*

import android.Manifest

const val USER_MAP = "USER_MAP"
private const val TAG = "CoreActivity"
class CoreActivity : AppCompatActivity(), GoogleMap.OnInfoWindowClickListener, OnMapReadyCallback{

    // Google Maps Variables
    private lateinit var mMap: GoogleMap
    private lateinit var userMap: Map
    private val LOCATION_PERMISSION_REQUEST = 1

    // Toolbar Variables - can think of creating toolbar but for now just use hello world?
    var drawerLayout: DrawerLayout? = null
    var navigationView: NavigationView? = null
    //    var textView: TextView? = null
    var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_core)

        // Hooks
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
//        textView = findViewById(R.id.hello_world)
        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

        val toggle : ActionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
//        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()



        // Generating Markers in Map
        val Map = generateCarparkData()

        // Actual Map Generation
//        userMap = intent.getSerializableExtra(USER_MAP) as Map
        userMap = Map[0]
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun generateCarparkData(): List<Map>{
        return listOf(
            Map(
                "Favourites",
                listOf(
                    Place("Block 757a HDB Woodlands (MSCP)", 1.44607, 103.79389, "23.5km away <br> <b>MON-FRI before 5/6pm</b> <br> $1.00 from 7am - 12pm. Free parking from 12pm to 2pm, $1 from 2pm to 5pm <br> <b>MON-FRI after 5/6pm</b> <br> $1.00/entry from 5pm to 12mn <br> $2.00/entry from 12mn to 7am the following day <br> <b>SAT</b> <br> Charges same as weekdays <br> <b>SUN/PUBLIC HOILDAYS</b>"),
                    Place("Block 739a HDB Woodlands (MSCP)", 1.44452, 103.79680, "25.5km away <br> <b>MON-FRI before 5/6pm</b> <br> \$1.00 from 7am - 12pm. Free parking from 12pm to 2pm, \$1 from 2pm to 5pm <br> <b>MON-FRI after 5/6pm</b> <br> \$1.00/entry from 5pm to 12mn <br> \$2.00/entry from 12mn to 7am the following day <br> <b>SAT</b> <br> Charges same as weekdays <br> <b>SUN/PUBLIC HOILDAYS</b>"),
                    Place("Northpoint City Car Park", 1.42751, 103.83713, "40.5km away <br> <b>MON-FRI before 5/6pm</b> <br> \$1.00 from 7am - 12pm. Free parking from 12pm to 2pm, \$1 from 2pm to 5pm <br> <b>MON-FRI after 5/6pm</b> <br> \$1.00/entry from 5pm to 12mn <br> \$2.00/entry from 12mn to 7am the following day <br> <b>SAT</b> <br> Charges same as weekdays <br> <b>SUN/PUBLIC HOILDAYS</b>"),
                    Place("Block 115B HDB Yishun (MSCP)", 1.43340, 103.82712, "60.5km away <br> <b>MON-FRI before 5/6pm</b> <br> \$1.00 from 7am - 12pm. Free parking from 12pm to 2pm, \$1 from 2pm to 5pm <br> <b>MON-FRI after 5/6pm</b> <br> \$1.00/entry from 5pm to 12mn <br> \$2.00/entry from 12mn to 7am the following day <br> <b>SAT</b> <br> Charges same as weekdays <br> <b>SUN/PUBLIC HOILDAYS</b>")
                )
            )
        )
    }

    // ------------- Google Maps Functions ---------------------------
    // Retrieve the current location
    private fun getLocationAccess(){
        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            mMap.isMyLocationEnabled = true
        }
        else
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),LOCATION_PERMISSION_REQUEST)
    }

    // App prompt user to allow access for location
    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        // Check if the user was granted access for location
        if(requestCode == LOCATION_PERMISSION_REQUEST){
            if(grantResults.contains(PackageManager.PERMISSION_GRANTED)){
                mMap.isMyLocationEnabled = true
            }
            else{
//                Toast.makeText(this, "User has not granted location access permission", Toast.LENGTH_LONG).show()
//                finish()
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
    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
//        var count = 0

//        while (!mMap.isMyLocationEnabled) {
            getLocationAccess()
//            count++
//        }


//        Log.i(TAG,"count: $count")

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
        val adapter : CustomInfoWindowAdapter = CustomInfoWindowAdapter(this@CoreActivity)
        mMap.setInfoWindowAdapter(adapter)

    }

    override fun onInfoWindowClick(marker: Marker) {
        Log.i(TAG,"marker: ${marker.position}")
        for (place in userMap.places){
            if (place.title == marker.title) {
                Log.i(TAG,"title: ${place.title}")
            }
        }
    }
}