package com.example.databaseapplication.mvp.views.interfaces

import com.example.databaseapplication.mvp.models.User

interface ProfileView: BaseMvpView{
    fun getInfoProfile(user: User)
    fun failedLoadData(msg:String)

}