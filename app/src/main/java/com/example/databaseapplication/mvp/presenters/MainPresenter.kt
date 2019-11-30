package com.example.databaseapplication.mvp.presenters

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.databaseapplication.adapters.UniversityListAdapter
import com.example.databaseapplication.mvp.LoadRepository
import com.example.databaseapplication.mvp.models.University
import com.example.databaseapplication.mvp.views.fragments.ListUniversityFragment
import com.example.databaseapplication.mvp.views.fragments.ProfileFragment
import com.example.databaseapplication.mvp.views.interfaces.MainView
import com.example.databaseapplication.network.GrantHelperApiService
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_university_list.*
import io.reactivex.android.schedulers.AndroidSchedulers

class MainPresenter(var mainView: MainView){

    lateinit var loadRepository: LoadRepository
    lateinit var apiService: GrantHelperApiService
    lateinit var mContext: Context

    var disposables = CompositeDisposable()
    init {
        loadRepository = LoadRepository()
    }
    //Сделать чтобы через систем сервис определял конекшн
    fun isOnline():Boolean{
        return true
    }

    @SuppressLint("CheckResult")
    fun getByParams(){
        val fr = ListUniversityFragment()
        mainView.changeFragment(fr)
        val adapter = fr.adapter
        loadRepository.getRecommedation()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                Log.i("start","start")
                for(i in it){
                    Log.i("start",i.name.toString())
                }
            },
            {

            })
    }

}