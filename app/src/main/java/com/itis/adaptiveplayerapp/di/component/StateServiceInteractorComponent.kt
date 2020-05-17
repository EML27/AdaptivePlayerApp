package com.itis.adaptiveplayerapp.di.component

import android.app.Activity
import com.itis.adaptiveplayerapp.di.module.ServiceInteractorModule
import com.itis.adaptiveplayerapp.ui.view.MainActivity
import dagger.Component


@Component(modules = [ServiceInteractorModule::class])
interface StateServiceInteractorComponent {
    fun inject(obj: Activity)
}
