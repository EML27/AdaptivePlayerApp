package com.itis.adaptiveplayerapp.ui.view

import android.annotation.SuppressLint
import com.itis.adaptiveplayerapp.R
import javax.inject.Inject
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.google.android.material.snackbar.Snackbar
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

        learn_switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewModel.startLearning()
            } else {
                viewModel.stopLearning()
            }
        }
        LocationRepresenter.checkAndAskForPermissions(this)

        help_btn.setOnClickListener {
            Snackbar.make(
                findViewById(R.id.activity_layout),
                "This allows us to listen to your music during some conditions. Help us to help you! =)",
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    override fun onStart() {
        super.onStart()
        val ld = viewModel.isServiceActive()
        ld.observeForever(Observer {
            if (it) {
                play_btn.text = "Stop"
                play_btn.setOnClickListener { viewModel.stopService() }
            } else {
                play_btn.text = "Start"
                play_btn.setOnClickListener { viewModel.startService() }
            }
        })

        val ldLearn = viewModel.isLearning()
        ldLearn.observeForever(Observer {
            learn_switch.isChecked = it
        })
    }


}
