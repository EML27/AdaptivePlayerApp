package com.itis.adaptiveplayerapp.di.component

import com.itis.adaptiveplayerapp.bl.StateReturner
import com.itis.adaptiveplayerapp.di.module.InjectForStateReturnerModule
import dagger.Component

@Component(modules = [InjectForStateReturnerModule::class])
interface InjectForStateReturnerComponent {
    fun inject(obj: StateReturner)
}
