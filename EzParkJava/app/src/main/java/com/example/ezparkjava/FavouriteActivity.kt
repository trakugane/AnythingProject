package com.example.ezparkjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ezparkjava.model.Map
import com.example.ezparkjava.model.Place
import kotlinx.android.synthetic.main.activity_favourite.*


const val USER_MAP = "USER_MAP"
private const val TAG = "MapsAdapter"
class FavouriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite)

        val Map = generateCarparkData()
        // Set layout manager on the recycler view
        rvMaps.layoutManager = LinearLayoutManager(this)
        // Set adapter on recycler view
        rvMaps.adapter = MapsAdapter(this, Map, object: MapsAdapter.OnClickListener{
            override fun onItemClick(position: Int) {
                Log.i(TAG, "Tapped on position $position")
                // When user taps on view, navigate to map activity
                val intent = Intent(this@FavouriteActivity, DisplayMapActivity::class.java)
                intent.putExtra(USER_MAP, Map[position])
                startActivity(intent)
            }
        })
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
}