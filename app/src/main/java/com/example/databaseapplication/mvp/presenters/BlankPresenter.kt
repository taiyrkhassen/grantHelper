package com.example.databaseapplication.mvp.presenters

import android.util.Log.i
import com.example.databaseapplication.mvp.views.interfaces.BlankView
import com.example.databaseapplication.network.GrantHelperApiService
import com.example.databaseapplication.network.NetworkClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject

class BlankPresenter(val view: BlankView) : BasePresenter() {

    var disposables = CompositeDisposable()
    private var retrofit = NetworkClient.getRetrofit()

    val apiService = retrofit.create(GrantHelperApiService::class.java)

    fun sendInfo(data: Map<String, Any>) {

        if (!checkInternetConnection()) {
            view.noInternetConnection()
        }

        disposables.add(
            apiService.sendInfo(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    when (it.code()) {
                        200 -> {
                            i("tag_send", "result: ${it.body().toString()}")
                             view.onSuccessSend()
                        }
                        else -> {
                            val jObjError = JSONObject(it.errorBody()!!.string())
                            try {
                                view.onFailed(" ${jObjError.getString("detail")}")
                            } catch (e: Exception) {
                                view.onFailed("Попробуйте позже, ${it.code()}")
                            }

                        }


                    }
                }, {

                })


        )


    }

}