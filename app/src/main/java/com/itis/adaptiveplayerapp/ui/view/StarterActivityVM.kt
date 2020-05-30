package com.itis.adaptiveplayerapp.ui.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.itis.adaptiveplayerapp.di.component.DaggerServiceInteractorComponent
import com.itis.adaptiveplayerapp.service.interactor.SpotifyServiceInteractor
import com.itis.adaptiveplayerapp.service.interactor.StateServiceInteractor
import javax.inject.Inject
import com.itis.adaptiveplayerapp.ui.viewmodel.ui.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StarterActivityVM @Inject constructor() : BaseViewModel() {

    init {
        DaggerServiceInteractorComponent.create().inject(this)
    }

    @Inject
    lateinit var stateServiceInteractor: StateServiceInteractor

    @Inject
    lateinit var spotifyServiceInteractor: SpotifyServiceInteractor

    fun isServiceActive(): LiveData<Boolean> = liveData {
        emit(false)
        stateServiceInteractor.liveD.observeForever {
            Observer<Boolean> { CoroutineScope(Dispatchers.Default).launch { emit(it) } }
        }
    }

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