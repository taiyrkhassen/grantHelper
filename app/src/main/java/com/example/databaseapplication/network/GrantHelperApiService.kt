package com.example.databaseapplication.network

import com.example.databaseapplication.mvp.models.ItemAdapter
import com.example.databaseapplication.mvp.models.Profession
import com.example.databaseapplication.mvp.models.University
import com.example.databaseapplication.mvp.models.User
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface GrantHelperApiService {
    companion object {
        val base_ul = "http://10.48.45.47:8000/"
    }

    @POST("getByMl/1/{id}")
    fun getSpecialities(@Path("id")id_user:Int): Observable<Response<ArrayList<ItemAdapter>>>

    @GET("university/{university_id}")
    fun getUniversityById(
        @Path("university_id") university_id:Int
    ) :Observable<Response<University>>

    @POST("create")
    fun sendInfo(
        @Body data:Map<String,@JvmSuppressWildcards Any>
    ) :Observable<Response<User>>

    @POST("profByInterests")
    fun sendFavourites(
        @Body list:ArrayList<String>
    ) :Observable<Response<ArrayList<Profession>>>

    @GET("get/{id}")
    fun getInfoProfileById(@Path("id") id:Int):Observable<Response<User>>

}