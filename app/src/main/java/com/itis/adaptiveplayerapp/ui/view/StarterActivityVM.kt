package com.itis.adaptiveplayerapp.ui.view

import com.itis.adaptiveplayerapp.di.component.DaggerServiceInteractorComponent
import com.itis.adaptiveplayerapp.service.interactor.SpotifyServiceInteractor
import com.itis.adaptiveplayerapp.service.interactor.StateServiceInteractor
import javax.inject.Inject
import com.itis.adaptiveplayerapp.ui.viewmodel.ui.BaseViewModel

class StarterActivityVM @Inject constructor() : BaseViewModel() {

    init {
        DaggerServiceInteractorComponent.create().inject(this)
    }

    @Inject
    lateinit var stateServiceInteractor: StateServiceInteractor

    @Inject
    lateinit var spotifyServiceInteractor: SpotifyServiceInteractor

    fun startService() {
        stateServiceInteractor.startStateService()
    }

    fun stopService() {
        stateServiceInteractor.stopStateService()
    }

    fun startSpotifyService() {
        spotifyServiceInteractor.startSpotifyPlayerService()
    }
}