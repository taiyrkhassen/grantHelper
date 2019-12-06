package com.example.databaseapplication.mvp.presenters

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import com.example.databaseapplication.DatabaseApp
import com.example.databaseapplication.mvp.views.interfaces.ListSpecialityView
import com.example.databaseapplication.network.GrantHelperApiService
import com.example.databaseapplication.network.NetworkClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import java.lang.Exception

class ListSpecialityPresenter(val view: ListSpecialityView) : BasePresenter() {

    var disposables = CompositeDisposable()
    private var retrofit = NetworkClient.getRetrofit()
    private val apiService: GrantHelperApiService = retrofit.create(GrantHelperApiService::class.java)
    private var mLoadedAll: Boolean = false
    private var mCurrentPage: Int = 1




    fun getListSpeciality(id:Int) {
        if(!checkInternetConnection()){
            view.noInternetConnection()
        }
        disposables.add(
            apiService.getSpecialities(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.code() == 200 || it.code() == 201) {
                        if (!it.body().isNullOrEmpty()) {
                            view.onListLoadded(it.body()!!)
                        } else {
                            view.onListLoadedFail("dfdf")
                        }
                    } else {
                        val jsonObj = JSONObject(it.errorBody()!!.toString())
                        try {
                            view.onListLoadedFail("dfdfsf")

                        } catch (exc: Exception) {
                            view.onListLoadedFail("Попробуйте позже -> ${it.code()}")
                        }
                    }
                }, {
                    view.onListLoadedFail(it.message!!)
                })
        )
    }

    override fun disposable() {
        disposables.dispose()
    }



}