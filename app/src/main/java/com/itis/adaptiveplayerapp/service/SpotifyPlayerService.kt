package com.itis.adaptiveplayerapp.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.itis.adaptiveplayerapp.BuildConfig
import com.itis.adaptiveplayerapp.DaggerAppComponent
import com.itis.adaptiveplayerapp.bl.MusicRecommender
import com.itis.adaptiveplayerapp.bl.dto.StateDto
import com.itis.adaptiveplayerapp.di.component.DaggerInjectForMusicRecommenderComponent
import com.itis.adaptiveplayerapp.di.component.DaggerInjectForSpotyServiceComponent
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import javax.inject.Inject

class SpotifyPlayerService : Service() {

    init {
        DaggerInjectForSpotyServiceComponent.create().inject(this)
    }

    @Inject
    lateinit var musicRecommender: MusicRecommender

    //if true, service would kill itself every time it finishes the command
    var selfStop = false

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
                    startMusic(
                        StateDto(
                            intent?.extras?.getString("weather"),
                            intent?.extras?.getString("time"),
                            intent?.extras?.getString("occupation")
                        )
                    )
                }
            }
        }
        if (selfStop) {
            this.stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun startMusic(state: StateDto) {
        val list = musicRecommender.getPlaylistByState(state)
        for (song in list) {
            mSpotifyAppRemote?.playerApi?.queue(song)
        }
        mSpotifyAppRemote?.playerApi?.resume()
    }

    //TODO specify the learning process

}
