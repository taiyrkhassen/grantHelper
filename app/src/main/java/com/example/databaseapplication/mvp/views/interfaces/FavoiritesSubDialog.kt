package com.example.databaseapplication.mvp.views.interfaces

import com.example.databaseapplication.mvp.models.Profession

interface FavoiritesSubDialog {
    fun successfullySend(listProfession: ArrayList<Profession>)
    fun failedSend(msg:String)
}