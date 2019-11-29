package com.example.databaseapplication.mvp.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.databaseapplication.R
import com.example.databaseapplication.mvp.presenters.MainPresenter
import com.example.databaseapplication.mvp.views.fragments.InformationAboutUsFragment
import com.example.databaseapplication.mvp.views.fragments.ListUniversityFragment
import com.example.databaseapplication.mvp.views.fragments.ProfileFragment
import com.example.databaseapplication.mvp.views.interfaces.MainView
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), MainView {


    lateinit var presenter: MainPresenter

    private lateinit var fragmentProfile:Fragment
    private var fragmentList:Fragment = ListUniversityFragment()
    private var fragmentInfo:Fragment = InformationAboutUsFragment()

    /**
     * Активный текущий фрагмент
     */
    lateinit private var active:Fragment
    private var fragmentManager = supportFragmentManager

    /**
     * Для навигации в интентах будем использовать TAB
     */
    companion object {
        const val TAB = "TAB"
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_profile -> {
                    changeFragment(fragmentProfile)
                    Log.i("start","changed1")
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_information ->{
                    changeFragment(fragmentInfo)
                    Log.i("start","changed2")
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_list->{
                    changeFragment(fragmentList)
                    Log.i("start","changed3")
                    return@OnNavigationItemSelectedListener true
                }

            }
            false
        }

    override fun noInternetConnection() {
        toast("No internet connection")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(this)
        fragmentProfile = ProfileFragment(presenter)
        active = fragmentProfile
        fragmentManager.beginTransaction().add(R.id.content, fragmentInfo, "3").hide(fragmentInfo).commit();
        fragmentManager.beginTransaction().add(R.id.content, fragmentList, "2").hide(fragmentList).commit();
        fragmentManager.beginTransaction().add(R.id.content,fragmentProfile, "1").commit();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


    }

    override fun changeFragment(fragment: Fragment) {
        if(presenter.isOnline()) {
            fragmentManager.beginTransaction().hide(active).show(fragment).commit()
            active = fragment
        } else {
            noInternetConnection()
        }
    }
    override fun targetFragment(): Fragment {
        return active
    }

}
