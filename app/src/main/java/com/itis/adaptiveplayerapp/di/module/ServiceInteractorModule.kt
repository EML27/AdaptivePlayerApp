package com.itis.adaptiveplayerapp.di.module

import com.itis.adaptiveplayerapp.service.interactor.StateInteractor
import dagger.Module
import dagger.Provides

@Module
class ServiceInteractorModule {
    @Provides
    fun getStateServiceInteractor(): StateInteractor{
        return StateInteractor()
    }
}