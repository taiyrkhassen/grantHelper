package com.example.databaseapplication.mvp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Profession(
    @SerializedName("id")
    val id:Int = 0,
    @SerializedName("name")
    val nameProfession:String?
):Serializable