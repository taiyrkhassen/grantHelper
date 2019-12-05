package com.example.databaseapplication.mvp.presenters

import com.example.databaseapplication.mvp.models.User
import com.example.databaseapplication.mvp.views.interfaces.ProfileView
import com.example.databaseapplication.network.GrantHelperApiService
import com.example.databaseapplication.network.NetworkClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import java.lang.Exception

class ProfilePresenter(val view: ProfileView) {

    var disposables = CompositeDisposable()


    private var retrofit = NetworkClient.getRetrofit()
    val apiService = retrofit.create(GrantHelperApiService::class.java)

    fun getUserInfo(id: Int) {
        disposables.add(
            apiService.getInfoProfileById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.code() == 200 || it.code() == 201) {
                        view.getInfoProfile(it.body()!!)
                    } else {
                        val jsonErrorObj = JSONObject(it.errorBody()!!.string())
                        try {
                            view.failedLoadData("${jsonErrorObj.getString("detail")}")
                        } catch (er: Exception) {
                            view.failedLoadData("Попробуйте позже - >${it.code()}")
                        }
                    }
                }, {
                    it.message?.let { it1 -> view.failedLoadData(it1) }
                })
        )
    }

}