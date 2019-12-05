package com.example.databaseapplication.mvp.views.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.databaseapplication.R
import com.example.databaseapplication.mvp.models.User
import com.example.databaseapplication.mvp.presenters.MainPresenter
import com.example.databaseapplication.mvp.presenters.ProfilePresenter
import com.example.databaseapplication.mvp.views.activities.BaseActivity
import com.example.databaseapplication.mvp.views.activities.BlankActivity
import com.example.databaseapplication.mvp.views.activities.MainActivity
import com.example.databaseapplication.mvp.views.activities.NoInternetConnectionActivity
import com.example.databaseapplication.mvp.views.interfaces.ProfileView
import kotlinx.android.synthetic.main.fragment_profile.*
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast


class ProfileFragment : Fragment(), ProfileView{

    private var presenter:ProfilePresenter = ProfilePresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // presenter.getUserInfo((activity as BaseActivity).getId())

        fragmentProfileButtonEdit.setOnClickListener {
            startActivity<BlankActivity>()
            (activity as BaseActivity).setLogged(false)
            (activity as BaseActivity).setId(0)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun getInfoProfile(user: User) {
        fragmentProfileUserName.text = user.first_name_user
        fragmentProfileAddress.text = "${user.city_user}, Kazakhstan"
        profile_first_prof_sub.text = user.prof_subject_one
        profile_second_prof_sub.text = user.prof_subject_two
        profile_language.text = user.language_user
        profile_entScore.text = "Балл ЕНТ: ${user.ent_score_user.toString()}"
    }

    override fun failedLoadData(msg: String) {
        toast(msg)
    }

    override fun noInternetConnection() {
        startActivity<NoInternetConnectionActivity>()
    }


}