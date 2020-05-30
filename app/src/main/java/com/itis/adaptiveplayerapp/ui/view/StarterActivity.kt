package com.itis.adaptiveplayerapp.ui.view

import com.itis.adaptiveplayerapp.R
import javax.inject.Inject
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.itis.adaptiveplayerapp.bl.gps.LocationRepresenter
import com.itis.adaptiveplayerapp.ui.viewmodel.di.qualifiers.ViewModelInjection
import com.itis.adaptiveplayerapp.ui.viewmodel.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_starter_activity.*

class StarterActivity : BaseActivity() {

    @Inject
    @ViewModelInjection
    lateinit var viewModel: StarterActivityVM

    override fun layoutRes() = R.layout.activity_starter_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.startSpotifyService()
        viewModel.isServiceActive().observeForever(Observer {
            if (it) {
                play_btn.text = "Stop"
                play_btn.setOnClickListener { viewModel.stopService() }
            } else {
                play_btn.text = "Start"
                play_btn.setOnClickListener { viewModel.startService() }
            }
        })

        LocationRepresenter.checkAndAskForPermissions(this)
    }
}
