package com.example.databaseapplication.mvp.views.interfaces

interface Contract {
    interface  Model {

    }
    interface View :BaseMvpView{
        fun onSuccesReload()
        fun isLoading()
    }
    interface Presenter{

    }
}