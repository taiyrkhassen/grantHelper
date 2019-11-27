package com.example.databaseapplication.mvp.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import com.example.databaseapplication.R
import com.example.databaseapplication.mvp.views.interfaces.MainView

class MainActivity : AppCompatActivity(), MainView {
    override fun noInternetConnection() {
        d("asdsad", "dsafsdf")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
