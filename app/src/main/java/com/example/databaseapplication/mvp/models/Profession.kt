package com.example.databaseapplication.mvp.models

import com.google.gson.annotations.SerializedName
import java.io.FileDescriptor
import java.io.Serializable


data class Profession(
    @SerializedName("id")
    val id:Int = 0,
    @SerializedName("professionName")
    val nameProfession:String?,
    @SerializedName("description")
    val description:String?

):Serializable, ItemAdapter()