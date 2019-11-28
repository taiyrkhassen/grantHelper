package com.example.databaseapplication.mvp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Speciality(
    @SerializedName("id")
    val id:Int,
    @SerializedName("name")
    val nameSpeciality: String?,
    @SerializedName("ent_score_russian")
    val entScoreRussian:Int?,
    @SerializedName("ent_score_kazakh")
    val entScoreKazakh:Int?
) : Serializable