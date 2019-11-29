package com.example.databaseapplication.mvp.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.databaseapplication.R
import com.example.databaseapplication.mvp.views.activities.NoInternetConnectionActivity
import kotlinx.android.synthetic.main.fragment_profile.*
import org.jetbrains.anko.support.v4.startActivity

class ProfileFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sd.setOnClickListener {
            startActivity<NoInternetConnectionActivity>()
        }
    }
}