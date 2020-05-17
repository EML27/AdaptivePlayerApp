package com.itis.adaptiveplayerapp.ui.view

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.itis.adaptiveplayerapp.BuildConfig
import com.itis.adaptiveplayerapp.R
import com.itis.adaptiveplayerapp.bl.MusicRecommender
import com.itis.adaptiveplayerapp.di.component.DaggerMusicRecommenderComponent
import com.itis.adaptiveplayerapp.di.component.DaggerStateServiceInteractorComponent
import com.itis.adaptiveplayerapp.service.StateService
import com.itis.adaptiveplayerapp.service.interactor.StateInteractor
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector.ConnectionListener
import com.spotify.android.appremote.api.SpotifyAppRemote
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private var mSpotifyAppRemote: SpotifyAppRemote? = null

    init {
        DaggerMusicRecommenderComponent.create().inject(this)
        DaggerStateServiceInteractorComponent.create().inject(this)
    }

    @Inject
    lateinit var musicRecommender: MusicRecommender

    @Inject
    lateinit var stateServiceInteractor: StateInteractor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView.text = musicRecommender.getRecommendedPlaylist()
        //play_btn.setOnClickListener { mSpotifyAppRemote?.playerApi?.play("spotify:playlist:37i9dQZF1DWXpOtMyVOt4Q") }

        play_btn.setOnClickListener {
            stateServiceInteractor.startStateService()
        }
    }

//    override fun onStart() {
//        super.onStart()
//        var connectionParams = ConnectionParams.Builder(BuildConfig.CLIENT_ID)
//            .setRedirectUri(BuildConfig.API_REDIRECT)
//            .showAuthView(true)
//            .build()
//
//        SpotifyAppRemote.connect(this, connectionParams,
//            object : ConnectionListener {
//                override fun onConnected(spotifyAppRemote: SpotifyAppRemote) {
//                    mSpotifyAppRemote = spotifyAppRemote
//                    Log.d("MainActivity", "Connected! Yay!")
//                    // Now you can start interacting with App Remote
//                    connected()
//                }
//
//                override fun onFailure(throwable: Throwable) {
//                    Log.e("MainActivity", throwable.message, throwable)
//                    // Something went wrong when attempting to connect! Handle errors here
//                }
//            })
//    }

    override fun onStop() {
        super.onStop()
//        SpotifyAppRemote.disconnect(mSpotifyAppRemote)
//        unbindService(mConnection)

    }
//
//    private fun connected() {
//
//    }


}
