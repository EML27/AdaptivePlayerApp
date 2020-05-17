package com.itis.adaptiveplayerapp.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.itis.adaptiveplayerapp.R
import com.itis.adaptiveplayerapp.bl.MusicRecommender
import com.itis.adaptiveplayerapp.di.component.DaggerMusicRecommenderComponent
import com.itis.adaptiveplayerapp.di.component.DaggerServiceInteractorComponent
import com.itis.adaptiveplayerapp.service.interactor.SpotifyServiceInteractor
import com.itis.adaptiveplayerapp.service.interactor.StateServiceInteractor
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

//    private var mSpotifyAppRemote: SpotifyAppRemote? = null

    init {
        DaggerMusicRecommenderComponent.create().inject(this)
        DaggerServiceInteractorComponent.create().inject(this)
    }

    @Inject
    lateinit var musicRecommender: MusicRecommender

    @Inject
    lateinit var stateServiceInteractor: StateServiceInteractor

    @Inject
    lateinit var spotifyServiceInteractor: SpotifyServiceInteractor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView.text = musicRecommender.getRecommendedPlaylist()
        //play_btn.setOnClickListener { mSpotifyAppRemote?.playerApi?.play("spotify:playlist:37i9dQZF1DWXpOtMyVOt4Q") }

        play_btn.setOnClickListener {
            stateServiceInteractor.startStateService("start")
            spotifyServiceInteractor.startSpotifyPlayerService()
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
