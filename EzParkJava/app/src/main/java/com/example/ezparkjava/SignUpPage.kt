package com.example.ezparkjava

import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sign_up_page.*


class SignUpPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_page)
    }

    fun mainPage(view: View) {
        finish()
    }

    fun signUp(view: View) {
        var firstName = editTextFirstName.text.toString()
        var lastName = editTextLastName.text.toString()
        var email = editTextEmail.text.toString()
        var phoneNum = editTextPhone.text.toString()
        var username = editTextUsername.text.toString()
        var password = editTextPassword.text.toString()
        var cPassword = editTextConfirmPassword.text.toString()
        var validCheck = true


        if (!PhoneNumberUtils.isGlobalPhoneNumber(phoneNum) || phoneNum.length < 8) {
            validCheck = false
            Toast.makeText(this, "Please enter a valid phone number", Toast.LENGTH_LONG).show()
        }
        else {
            validCheck = true
        }

        if (!email.isEmailValid()) {
            validCheck = false
            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_LONG).show()
        }
        else {
            validCheck = true
        }

        if (!isValidPassword(password)) {
            validCheck = false
            Toast.makeText(this, "Your password should consist of 1 Special Character and 1 Uppercase Character with a minimum length of 8", Toast.LENGTH_LONG).show()
        }
        else {
            if (password != cPassword) {
                validCheck = false
                Toast.makeText(this, "Passwords does not match", Toast.LENGTH_LONG).show()
            }
            else
            {
                validCheck = true
            }
        }

        if (validCheck) {
            Toast.makeText(this, "CLEAR", Toast.LENGTH_LONG).show()
        }


    }

    fun String.isEmailValid(): Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    fun isValidPassword(password: String?) : Boolean {
        password?.let {
            val passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[#?!@\$%^&*]).{8,}$"
            val passwordMatcher = Regex(passwordPattern)

            return passwordMatcher.find(password) != null
        } ?: return false
    }


}