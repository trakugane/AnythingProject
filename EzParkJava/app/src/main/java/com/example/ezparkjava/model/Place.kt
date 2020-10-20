package com.example.ezparkjava.model

import java.io.Serializable

data class Place(val title: String, val latitude: Double, val longtitude: Double, val details: String) : Serializable