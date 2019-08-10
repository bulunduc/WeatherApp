package com.bulunduc.weatherapp

import android.content.SharedPreferences
import android.util.Log
const val APP_PREFERENCE = "weatherAppSavings"
const val APP_CURRENT_CITY = "city"

fun saveCurrentCity(preferences: SharedPreferences, city : String) {
    val editor = preferences.edit()
    editor.putString(APP_CURRENT_CITY, city)
    editor.apply()
}

fun getCurrentCity(preferences: SharedPreferences) : String{
    return preferences.getString(APP_CURRENT_CITY, "")
}
