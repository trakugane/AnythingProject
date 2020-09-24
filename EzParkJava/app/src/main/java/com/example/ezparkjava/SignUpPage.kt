package com.example.ezparkjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SignUpPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_page)
    }

    fun mainPage(view: View) {
        finish()
    }
}