package com.itis.adaptiveplayerapp.di.component

import com.itis.adaptiveplayerapp.di.module.StateReturnerModule
import dagger.Component

@Component(modules = [StateReturnerModule::class])
interface StateReturnerComponent {
    fun inject(obj: Any)
}