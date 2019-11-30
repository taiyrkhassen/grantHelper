package com.example.databaseapplication.mvp.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.databaseapplication.R
<<<<<<< HEAD
import com.example.databaseapplication.mvp.presenters.MainPresenter
import kotlinx.android.synthetic.main.fragment_profile.view.*
=======
import com.example.databaseapplication.mvp.views.activities.NoInternetConnectionActivity
import kotlinx.android.synthetic.main.fragment_profile.*
import org.jetbrains.anko.support.v4.startActivity
>>>>>>> 9fad186c4e1d630b84c4f8dcff2ca662ac1de019

class ProfileFragment(var presenter: MainPresenter) : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
<<<<<<< HEAD
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        view.material_button.setOnClickListener{
            presenter.getByParams()
        }
        return view
=======

        return inflater.inflate(R.layout.fragment_profile, container, false)
>>>>>>> 9fad186c4e1d630b84c4f8dcff2ca662ac1de019
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setOnClickListener {
//            startActivity<NoInternetConnectionActivity>()
//        }
    }
}