package com.example.databaseapplication.mvp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class University(
    @SerializedName("id")
    val id:Int?,
    @SerializedName("name")
    val name:String?,
    @SerializedName("phone_number")
    val phone:String?,
    @SerializedName("address")
    val address: String,
    @SerializedName("speciality")
    val speciality: Speciality,
    @SerializedName("image_small")
    val imageSmall:String?
): Serializable