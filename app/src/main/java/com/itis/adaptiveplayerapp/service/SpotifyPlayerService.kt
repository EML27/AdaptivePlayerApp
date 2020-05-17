package com.itis.adaptiveplayerapp.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.itis.adaptiveplayerapp.BuildConfig
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote

class SpotifyPlayerService : Service() {

    override fun onBind(intent: Intent): IBinder {
        return SpotyBinder()
    }

    inner class SpotyBinder : Binder() {
        fun getService() = this@SpotifyPlayerService
    }

    private var mSpotifyAppRemote: SpotifyAppRemote? = null

    override fun onCreate() {
        super.onCreate()
        var connectionParams = ConnectionParams.Builder(BuildConfig.CLIENT_ID)
            .setRedirectUri(BuildConfig.API_REDIRECT)
            .showAuthView(true)
            .build()

        SpotifyAppRemote.connect(this, connectionParams,
            object : Connector.ConnectionListener {
                override fun onConnected(spotifyAppRemote: SpotifyAppRemote) {
                    mSpotifyAppRemote = spotifyAppRemote
                    Log.d("SpotifyService", "Connected! Yay!")
                    // Now you can start interacting with App Remote
                    connected()
                }

                override fun onFailure(throwable: Throwable) {
                    Log.e("SpotifyService", throwable.message, throwable)
                    // Something went wrong when attempting to connect! Handle errors here
                }
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        SpotifyAppRemote.disconnect(mSpotifyAppRemote)

    }

    private fun connected() {

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent.let {
            when (it?.action) {
                "start" -> {
                    startMusic()
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    fun startMusic() {
        mSpotifyAppRemote?.playerApi?.play("spotify:playlist:37i9dQZF1DWXpOtMyVOt4Q")
    }

}
