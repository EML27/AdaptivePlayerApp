package com.itis.adaptiveplayerapp.service.interactor

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.itis.adaptiveplayerapp.App
import com.itis.adaptiveplayerapp.service.StateService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StateServiceInteractor @Inject constructor() {

    var isBound = false
    var liveD: MutableLiveData<Boolean> = MutableLiveData(false)
    var isLearning: MutableLiveData<Boolean> = MutableLiveData(false)
    var stateService: StateService? = null
    private var mConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            stateService = null

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as StateService.MyBinder
            stateService = binder.getService()

        }
    }

    fun bind() {
        App.getInstance()?.bindService(
            Intent(App.getInstance(), StateService::class.java),
            mConnection,
            Context.BIND_AUTO_CREATE
        )
        isBound = true
    }

    fun unbind() {
        if (isBound) {
            App.getInstance()?.unbindService(mConnection)
            isBound = false
        }
    }

    fun startLearning() {
        App.getInstance()?.startService(Intent(App.getInstance(), StateService::class.java).apply {
            action = "learn"
        })
        isLearning.value = true
    }

    fun stopLearning() {
        App.getInstance()?.startService(Intent(App.getInstance(), StateService::class.java).apply {
            action = "stop_learn"
        })
        isLearning.value = false
    }

    fun startStateService() {
        App.getInstance()?.startService(Intent(App.getInstance(), StateService::class.java).apply {
            action = "start"
        })
        bind()
        liveD.value = true
    }

    fun stopStateService() {

        App.getInstance()?.startService(Intent(App.getInstance(), StateService::class.java).apply {
            action = "stop"
        })
        liveD.value = false
        isLearning.value=false
    }
}