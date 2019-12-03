package com.example.databaseapplication

import android.app.Application
import android.content.Context

class DatabaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
    }
    companion object {
        private lateinit var mContext: Context
        fun getContext(): Context {
            return mContext
        }
    }
}