package com.example.databaseapplication.mvp.views.interfaces

import com.example.databaseapplication.mvp.models.University

interface ListSpecialityView: BaseMvpView{

    fun onListLoading(isDone:Boolean)
    fun onListLoadedFail(msg:String)
    fun onListLoadded(arrayList: ArrayList<University>)
    fun ListEmpty()
}