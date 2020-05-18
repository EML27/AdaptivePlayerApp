package com.itis.adaptiveplayerapp.di.component

import com.itis.adaptiveplayerapp.di.module.ServiceInteractorModule
import dagger.Component


@Component(modules = [ServiceInteractorModule::class])
interface ServiceInteractorComponent {
    fun inject(obj: Any)
}
