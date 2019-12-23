package com.example.databaseapplication.mvp.models

import com.google.gson.annotations.SerializedName

data class CheckBoxModel(
    var textBox:String ="",
    var isCheckedModel:Boolean = false
)