package com.example.databaseapplication.mvp.views.interfaces

interface BlankView:BaseMvpView{

    fun onFailed(msg:String)
    fun onSuccessSend()

}