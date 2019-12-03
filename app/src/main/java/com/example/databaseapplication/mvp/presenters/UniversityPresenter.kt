package com.example.databaseapplication.mvp.presenters

import android.annotation.SuppressLint
import com.example.databaseapplication.mvp.models.University
import com.example.databaseapplication.mvp.views.activities.DetailUniversityActivity
import com.example.databaseapplication.mvp.views.interfaces.DetailUniversityView
import com.example.databaseapplication.network.GrantHelperApiService
import com.example.databaseapplication.network.NetworkClient
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class UniversityPresenter(val viewState: DetailUniversityView) {

    private var retrofit = NetworkClient.getRetrofit()
    var disposables = CompositeDisposable()
    val apiService = retrofit.create(GrantHelperApiService::class.java)


    @SuppressLint("CheckResult")
    fun getUniversity(id: Int) {
        disposables.add(
            apiService.getUniversityById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    //success
                    if (it.code() == 200 || it.code() == 201) {
                        viewState.onUniversityLoaded(it.body()!!)
                    } else {
                        val jsonErrorObj = JSONObject(it.errorBody()!!.string())
                        try {
                            viewState.onUniversityLoadFail("${jsonErrorObj.getString("detail")}")
                        } catch (er: Exception) {
                            viewState.onUniversityLoadFail("Попробуйте позже - >${it.code()}")
                        }
                    }

                }, {
                    try {
                        viewState.onUniversityLoadFail(it.message!!)
                    } catch (er: Exception) {
                    }

                })
        )
    }

}