package com.example.databaseapplication.mvp.views.activities

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.databaseapplication.R
import com.example.databaseapplication.mvp.views.activities.MainActivity.Companion.TAB
import com.example.databaseapplication.mvp.views.interfaces.InternetConnectionView
import com.example.databaseapplication.ui.CustomPlaceholderView
import kotlinx.android.synthetic.main.nointernet_activity.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class NoInternetConnectionActivity : BaseActivity(), InternetConnectionView {
    private lateinit var placeHold: CustomPlaceholderView
    override fun placeHolderActionCallback(o: Any) {
        if (isOnline()) {
            if (isLoggedIn()) {
                startActivity<MainActivity>(
                    TAB to 3
                )
            } else {
                startActivity<BlankActivity>()
            }
        } else {
            toast("Все еще нет интернета")
        }

    }

    //private var customPlace: CustomPlaceholderView = noInternetConnectionPlaceHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nointernet_activity)
        placeHold = findViewById(R.id.noInternetConnectionPlaceHolder_activity)
        placeHold.setOnActionClickListener(this)
    }


}