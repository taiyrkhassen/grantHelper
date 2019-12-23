package com.example.databaseapplication.mvp.presenters

import android.util.Log
import com.example.databaseapplication.mvp.views.interfaces.FavoiritesSubDialog
import com.example.databaseapplication.network.GrantHelperApiService
import com.example.databaseapplication.network.NetworkClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject

class DialogPresenterFavourite(val view: FavoiritesSubDialog) : BasePresenter() {

    var disposables = CompositeDisposable()
    private var retrofit = NetworkClient.getRetrofit()
    private val apiService: GrantHelperApiService =
        retrofit.create(GrantHelperApiService::class.java)

    fun sendFavourites(list: ArrayList<String>) {
        disposables.add(
            apiService.sendFavourites(list)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    when (it.code()) {
                        200 -> {
                            Log.i("tag_fav", "result: ${it.body().toString()}")
                            view.successfullySend(it.body()!!)
                        }
                        201 -> {
                            Log.i("tag_fav", "result: ${it.body().toString()}")
                            view.successfullySend(it.body()!!)
                        }
                        else ->{
                            val jObjError = JSONObject(it.errorBody()!!.string())
                            try {
                                view.failedSend(" ${jObjError.getString("detail")}")
                            } catch (e: Exception) {
                                view.failedSend("Попробуйте позже, ${it.code()}")
                            }
                        }
                    }

                }, {
                    view.failedSend(it.message!!)
                })
        )
    }

    override fun disposable() {
        disposables.dispose()
    }
}