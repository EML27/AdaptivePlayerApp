package com.itis.adaptiveplayerapp.di.module

import com.itis.adaptiveplayerapp.service.interactor.SpotifyServiceInteractor
import com.itis.adaptiveplayerapp.service.interactor.StateServiceInteractor
import dagger.Module
import dagger.Provides

@Module
class ServiceInteractorModule {
    @Provides
    fun getStateServiceInteractor(): StateServiceInteractor{
        return StateServiceInteractor()
    }

    @Provides
    fun getSpotifyServiceInteractor(): SpotifyServiceInteractor{
        return SpotifyServiceInteractor()
    }
}