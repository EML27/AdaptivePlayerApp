package com.itis.adaptiveplayerapp.di.component

import com.itis.adaptiveplayerapp.di.module.StateModule
import com.itis.adaptiveplayerapp.di.module.StateReturnerModule
import dagger.Component

@Component(modules = [StateModule::class])
interface StateComponent {
    fun inject(obj: Any)
}