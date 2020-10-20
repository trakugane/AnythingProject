package com.example.ezparkjava

//import android.R.layout
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth

//import com.example.ezparkjava.model.Map
//import com.example.ezparkjava.model.Place
//
//const val USER_MAP = "USER_MAP"
//private const val TAG = "MapsAdapter"
class MainActivity : AppCompatActivity() {
//    lateinit var ezPark_Dao: ezParkDao
//    lateinit var ezPark_Db: ezParkDb

    private lateinit var auth: FirebaseAuth

    lateinit var SignIn: SignInButton
    var RC_SIGN_IN = 0
    var mGoogleSignInClient: GoogleSignInClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val Map = generateCarparkData()

//        auth = FirebaseAuth.getInstance()
//        val user = FirebaseAuth.getInstance().currentUser

        SignIn = findViewById(R.id.sign_in_button)
        SignIn.setOnClickListener(View.OnClickListener { view ->
            when (view.id) {
                R.id.sign_in_button -> signIn()
            }
        })

//        val yourDefaultWebClientId = 253566006779
        val gso : GoogleSignInOptions =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    private fun signIn() {
        val signInIntent : Intent = mGoogleSignInClient!!.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task : Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account : GoogleSignInAccount = completedTask.getResult(ApiException::class.java)!!

            // Signed in successfully, show authenticated UI.
            val intent = Intent(this, FavouriteActivity::class.java)
            startActivity(intent)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Error", "signInResult:failed code=" + e.statusCode)
        }
    }

//    private fun generateCarparkData(): List<Map>{
//        return listOf(
//            Map("Favourites",
//                listOf(
//                    Place("Block 757a HDB Woodlands (MSCP)",1.44607,103.79389),
//                    Place("Block 739a HDB Woodlands (MSCP)",1.44452,103.79680),
//                    Place("Northpoint City Car Park",1.42751,103.83713),
//                    Place("Block 115B HDB Yishun (MSCP)",1.43340,103.82712)
//                ))
//        )
//
//    }

    fun signUpPage(view: View) {
        val intent = Intent(this, SignUpPage::class.java)
        startActivity(intent)
    }

}