package com.itis.adaptiveplayerapp.global_app

import android.app.Application
import android.content.Context
import dagger.Component

@Component
class MainApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private lateinit var instance: MainApplication

        fun applicationContext() : Context {
            return instance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        // initialize for any

        // Use ApplicationContext.
        // example: SharedPreferences etc...
        val context: Context = MainApplication.applicationContext()
    }
}