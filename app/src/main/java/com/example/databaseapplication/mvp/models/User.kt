package com.example.databaseapplication.mvp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("ent_score_user")
    val ent_score_user:Int?,
    @SerializedName("first_name_user")
    var first_name_user:String?,
    @SerializedName("last_name_user")
    val last_name_user:String?,
    @SerializedName("city_user")
    val city_user:String?,
    @SerializedName("language_user")
    val language_user:String?,
    @SerializedName("prof_subject_one")
    val prof_subject_one:String,
    @SerializedName("prof_subject_two")
    val prof_subject_two:String,
    @SerializedName("id")
    val id_user:Int = 0
    ):Serializable
