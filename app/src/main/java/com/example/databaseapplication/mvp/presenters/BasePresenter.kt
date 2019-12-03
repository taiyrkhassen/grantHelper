package com.example.databaseapplication.mvp.presenters

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import com.example.databaseapplication.DatabaseApp

abstract class BasePresenter {

    val context = DatabaseApp.getContext()

    fun checkInternetConnection(): Boolean{
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.activeNetwork != null
        } else {
            val netInfo = connectivityManager.activeNetworkInfo
            netInfo != null && netInfo.isConnectedOrConnecting
        }
    }
}