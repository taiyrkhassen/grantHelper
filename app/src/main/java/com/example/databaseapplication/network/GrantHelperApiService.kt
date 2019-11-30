package com.example.databaseapplication.network

import com.example.databaseapplication.mvp.models.University
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GrantHelperApiService{
    companion object{
        val base_ul = "http://192.168.1.115:8000/speciality/"
    }
    //Здесь может быть ошибка
    interface GSON {
        @GET("university/")
        fun getAll(): Observable<ArrayList<University>>
    }
}