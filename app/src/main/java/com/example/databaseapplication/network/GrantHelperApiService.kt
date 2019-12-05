package com.example.databaseapplication.network

import com.example.databaseapplication.mvp.models.University
import com.example.databaseapplication.mvp.models.User
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface GrantHelperApiService {
    companion object {
        val base_ul = "http://192.168.1.115:8000/speciality/"
    }

    @GET("university/")
    fun getSpecialities(): Observable<Response<ArrayList<University>>>

    @GET("university/{university_id}")
    fun getUniversityById(
        @Path("university_id") university_id:Int
    ) :Observable<Response<University>>

    @GET("")
    fun sendInfo(
        @Body data:Map<String,@JvmSuppressWildcards Any>
    ) :Observable<Response<User>>

    @GET("")
    fun getInfoProfileById(id:Int):Observable<Response<User>>

}