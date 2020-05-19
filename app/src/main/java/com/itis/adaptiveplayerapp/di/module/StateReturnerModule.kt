package com.itis.adaptiveplayerapp.di.module

import com.itis.adaptiveplayerapp.bl.StateReturner
import dagger.Module
import dagger.Provides

@Module
class StateReturnerModule {
    @Provides
    fun getStateReturner(): StateReturner{
        return StateReturner()
    }
}