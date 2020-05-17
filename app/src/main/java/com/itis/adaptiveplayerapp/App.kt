package com.itis.adaptiveplayerapp

import android.app.Application
var instance: App? =null

class App : Application() {



    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun onTerminate() {
        super.onTerminate()
        instance=null
    }

    companion object{
        fun getInstance() = instance
    }

}