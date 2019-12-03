package com.example.databaseapplication.mvp.views.interfaces

import com.example.databaseapplication.mvp.models.University

interface DetailUniversityView: BaseMvpView{
    fun onUniversityLoadFail(msg:String)
    fun onUniversityLoaded(university:University)
    fun onUniverEmpty()
}