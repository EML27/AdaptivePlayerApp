package com.itis.adaptiveplayerapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector.ConnectionListener
import com.spotify.android.appremote.api.SpotifyAppRemote
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var  mSpotifyAppRemote: SpotifyAppRemote?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        play_btn.setOnClickListener { mSpotifyAppRemote?.playerApi?.play("spotify:playlist:37i9dQZF1DWXpOtMyVOt4Q") }
    }

    override fun onStart() {
        super.onStart()
        var connectionParams = ConnectionParams.Builder(BuildConfig.CLIENT_ID)
                .setRedirectUri(BuildConfig.API_REDIRECT)
                .showAuthView(true)
                .build()

        SpotifyAppRemote.connect(this, connectionParams,
                object : ConnectionListener {
                    override fun onConnected(spotifyAppRemote: SpotifyAppRemote) {
                        mSpotifyAppRemote = spotifyAppRemote
                        Log.d("MainActivity", "Connected! Yay!")
                        // Now you can start interacting with App Remote
                        connected()
                    }

                    override fun onFailure(throwable: Throwable) {
                        Log.e("MainActivity", throwable.message, throwable)
                        // Something went wrong when attempting to connect! Handle errors here
                    }
                })
    }

    override fun onStop() {
        super.onStop()
        SpotifyAppRemote.disconnect(mSpotifyAppRemote)
    }

    private fun connected(){

    }
}
