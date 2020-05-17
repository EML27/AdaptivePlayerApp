package com.itis.adaptiveplayerapp.service.interactor

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.itis.adaptiveplayerapp.App
import com.itis.adaptiveplayerapp.service.SpotifyPlayerService
import javax.inject.Inject

class SpotifyServiceInteractor @Inject constructor() {


    var spotifyPlayerService: SpotifyPlayerService? = null
    private var mConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            spotifyPlayerService = null
            //Toast.makeText(this@TrackActivity,"Entered onServiceDisconnected block", Toast.LENGTH_SHORT).show()
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as SpotifyPlayerService.SpotyBinder
            spotifyPlayerService = binder.getService()
            //Toast.makeText(this@TrackActivity,"Entered onServiceConnected block", Toast.LENGTH_SHORT).show()
        }
    }

    fun bind() =
        App.getInstance()?.bindService(
            Intent(App.getInstance(), SpotifyPlayerService::class.java),
            mConnection,
            Context.BIND_AUTO_CREATE
        )


    fun startSpotifyPlayerService() {
        App.getInstance()
            ?.startService(Intent(App.getInstance(), SpotifyPlayerService::class.java).apply {
                action = "start"
            })
    }
}