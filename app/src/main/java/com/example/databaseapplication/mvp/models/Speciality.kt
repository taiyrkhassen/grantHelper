package com.example.databaseapplication.mvp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Speciality(
    @SerializedName("id")
    val id:Int= 0,
    @SerializedName("name")
    val nameSpeciality: String?,
    @SerializedName("ent_score_russian")
    val entScoreRussian:Int?,
    @SerializedName("ent_score_kazakh")
    val entScoreKazakh:Int?,
    @SerializedName("profession")
    val profession: Profession,
    @SerializedName("chance_score")
    val chance_score: Double
) : Serializable