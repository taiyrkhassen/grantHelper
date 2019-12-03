package com.example.databaseapplication.mvp.presenters

import com.example.databaseapplication.mvp.views.interfaces.ProfileView
import com.example.databaseapplication.network.GrantHelperApiService
import com.example.databaseapplication.network.NetworkClient
import io.reactivex.disposables.CompositeDisposable

class ProfilePresenter(view:ProfileView){
    var disposables = CompositeDisposable()

    private var viewState = object: ProfileView{
        override fun getInfoProfile() {

        }

    }
    private var retrofit = NetworkClient.getRetrofit()
    val apiService = retrofit.create(GrantHelperApiService::class.java)

    fun getInfo(){


    }
}