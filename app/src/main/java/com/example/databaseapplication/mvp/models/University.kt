package com.example.databaseapplication.mvp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable
data class University(
    @SerializedName("id")
    val id:Int =0,
    @SerializedName("universityName")
    val name:String?,
    @SerializedName("phone_number")
    val phone:String?,
    @SerializedName("universityCity")
    val address: String?,
    @SerializedName("speciality")
    val speciality: Speciality,
    @SerializedName("image_small")
    val imageSmall:String?,
    @SerializedName("university_description")
    val description:String?,
    @SerializedName("university_web_url")
    val url:String?
): Serializable