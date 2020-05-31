package com.itis.adaptiveplayerapp.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.itis.adaptiveplayerapp.BuildConfig

import com.itis.adaptiveplayerapp.bl.MusicRecommender
import com.itis.adaptiveplayerapp.bl.dto.StateDto
import com.itis.adaptiveplayerapp.di.component.DaggerInjectForMusicRecommenderComponent
import com.itis.adaptiveplayerapp.di.component.DaggerInjectForSpotyServiceComponent
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
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
                "learn" -> {
                    learn(
                        StateDto(
                            intent?.extras?.getString("weather"),
                            intent?.extras?.getString("time"),
                            intent?.extras?.getString("occupation")
                        )
                    )
                }
                "pause" -> pauseMusic()
            }
        }
        if (selfStop) {
            this.stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    fun pauseMusic() {
        mSpotifyAppRemote?.playerApi?.pause()
    }

    private fun startMusic(state: StateDto) {
        CoroutineScope(Dispatchers.Default).launch {
            val list = musicRecommender.getPlaylistByState(state)
            for (song in list) {
                mSpotifyAppRemote?.playerApi?.queue(song)
            }
            mSpotifyAppRemote?.playerApi?.resume()
        }
    }

    var lastSong: String? = null

    private fun learn(state: StateDto) {
//        CoroutineScope(Dispatchers.Unconfined).launch {
//            val playerStateCall = mSpotifyAppRemote?.playerApi?.playerState
//            val playerStateCallResult = playerStateCall?.await(10, TimeUnit.SECONDS)
//            var currentSong: String? = null
//            if (playerStateCallResult?.isSuccessful == true) {
//                currentSong = playerStateCallResult.data.track.uri
//                Log.i("SpotifyService", "Spotify song is adding... $currentSong")
//            } else {
//                Log.e("SpotifyService", "Error with getting song uri from player")
//                playerStateCallResult?.error?.printStackTrace()
//            }
//            if (currentSong != null) {
//                Log.i("SpotifyService", "condition currentSong!=null accessed. Current song: $currentSong")
//                musicRecommender.learn(state, currentSong)
//            }
//            Log.i("SpotifyService", "Learn method finished")
//        }

        mSpotifyAppRemote?.playerApi?.subscribeToPlayerState()
            ?.setEventCallback { playerState -> lastSong = playerState.track.uri }
            ?.setErrorCallback { throwable ->
                Log.e("SpotifyService", "Смэрть")
                throwable.printStackTrace()
            }
        CoroutineScope(Dispatchers.IO).launch {
            musicRecommender.learn(state, lastSong ?: "")
        }

    }

    companion object {
        fun startMusic(context: Context, state: StateDto) {
            context.startService(Intent(context, SpotifyPlayerService::class.java).apply {
                action = "start"
                putExtra("weather", state.weather)
                putExtra("time", state.time)
                putExtra("occupation", state.occupation)
            })
        }

        fun startLearning(context: Context, state: StateDto) {
            context.startService(Intent(context, SpotifyPlayerService::class.java).apply {
                action = "learn"
                putExtra("weather", state.weather)
                putExtra("time", state.time)
                putExtra("occupation", state.occupation)
            })
        }

        fun pauseMusic(context: Context) {
            context.startService(Intent(context, SpotifyPlayerService::class.java).apply {
                action = "pause"
            })
        }
    }

}
