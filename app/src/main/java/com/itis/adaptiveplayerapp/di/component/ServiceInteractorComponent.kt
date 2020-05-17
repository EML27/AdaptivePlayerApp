package com.itis.adaptiveplayerapp.di.component

import android.app.Activity
import android.content.Context
import com.itis.adaptiveplayerapp.di.module.ServiceInteractorModule
import com.itis.adaptiveplayerapp.ui.view.MainActivity
import dagger.Component


@Component(modules = [ServiceInteractorModule::class])
interface ServiceInteractorComponent {
    fun inject(obj: Context)
}
