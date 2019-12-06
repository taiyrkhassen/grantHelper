package com.example.databaseapplication.mvp.views.activities

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.databaseapplication.mvp.models.User
import kotlin.math.log

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = getSharedPreferences("pref_name", Context.MODE_PRIVATE)
    }

    fun isLoggedIn(): Boolean {
        return sharedPref.getBoolean("is_logged_in", false)
    }

    fun setLogged(loggedIn: Boolean) {
        sharedPref.edit().putBoolean("is_logged_in", loggedIn).apply()
    }

    fun getId(): Int {
        return sharedPref.getInt("user_id", 1)
    }

    fun setId(id: Int) {
        sharedPref.edit().putInt("user_id", id).apply()
    }


    fun isOnline(): Boolean {
        val cm = (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?)!!
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }

}