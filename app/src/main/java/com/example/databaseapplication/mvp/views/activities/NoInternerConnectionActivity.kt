package com.example.databaseapplication.mvp.views.activities

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.databaseapplication.R
import com.example.databaseapplication.mvp.views.interfaces.InternetConnectionView

class NoInternetConnectionActivity : AppCompatActivity(), InternetConnectionView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nointernet_activity)
    }
 


    fun isOnline(): Boolean {
        val cm = (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?)!!
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
}