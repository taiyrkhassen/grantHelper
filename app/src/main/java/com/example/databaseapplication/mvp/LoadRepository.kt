package com.example.databaseapplication.mvp

import com.example.databaseapplication.mvp.models.University
import com.example.databaseapplication.network.GrantHelperApiService
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class LoadRepository {
    companion object{
        var retrofit:Retrofit? = null
    }
    fun getRecommedation():Observable<ArrayList<University>>?{
        //Здесь должны быть параметры
        return getRetrofit()?.create(GrantHelperApiService.GSON::class.java)?.getAll()
    }
    fun getRetrofit():Retrofit?{
        if (retrofit == null){
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            retrofit = Retrofit.Builder()
                .baseUrl(GrantHelperApiService.base_ul)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
        return retrofit
    }
}