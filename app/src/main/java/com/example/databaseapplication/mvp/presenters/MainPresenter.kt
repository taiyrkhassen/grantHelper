package com.example.databaseapplication.mvp.presenters

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.databaseapplication.adapters.UniversityListAdapter
import com.example.databaseapplication.mvp.models.University
import com.example.databaseapplication.mvp.views.fragments.ListUniversityFragment
import com.example.databaseapplication.mvp.views.fragments.ProfileFragment
import com.example.databaseapplication.mvp.views.interfaces.MainView
import com.example.databaseapplication.network.GrantHelperApiService
import com.example.databaseapplication.network.NetworkClient
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_university_list.*
import io.reactivex.android.schedulers.AndroidSchedulers

class MainPresenter(var mainView: MainView): BasePresenter(){

    override fun disposable() {

    }

    var disposables = CompositeDisposable()
    private var retrofit = NetworkClient.getRetrofit()
    val apiService = retrofit.create(GrantHelperApiService::class.java)



}