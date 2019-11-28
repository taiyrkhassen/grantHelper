package com.example.databaseapplication.mvp.presenters

import android.app.Application
import android.content.Context
import com.example.databaseapplication.mvp.views.interfaces.MainView
import com.example.databaseapplication.network.GrantHelperApiService
import io.reactivex.disposables.CompositeDisposable

class MainPresenter(mainView: MainView){

    lateinit var apiService: GrantHelperApiService
    lateinit var mContext: Context

    var disposables = CompositeDisposable()

    //Сделать чтобы через систем сервис определял конекшн
    fun isOnline():Boolean{
        return true
    }

}