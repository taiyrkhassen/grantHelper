package com.example.databaseapplication.mvp.views.interfaces

import androidx.fragment.app.Fragment

interface MainView :BaseMvpView{
    fun changeFragment(fragment: Fragment)
    fun targetFragment():Fragment
}