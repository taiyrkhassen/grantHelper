package com.example.databaseapplication.mvp.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.databaseapplication.R
import com.example.databaseapplication.mvp.presenters.MainPresenter
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment(var presenter: MainPresenter) : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        view.material_button.setOnClickListener{
            presenter.getByParams()
        }
        return view
    }
}