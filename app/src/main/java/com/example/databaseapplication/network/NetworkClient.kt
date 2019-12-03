package com.example.databaseapplication.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClient {

    fun getRetrofit(): Retrofit{
        var retrofit:Retrofit? = null
        if(retrofit==null){
            retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(GrantHelperApiService.base_ul)
                .build()
        }
        return retrofit!!
    }
}