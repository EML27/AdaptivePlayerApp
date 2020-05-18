package com.itis.adaptiveplayerapp.ui.view

import com.itis.adaptiveplayerapp.R
import javax.inject.Inject
import android.os.Bundle
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
        play_btn.setOnClickListener { viewModel.startService() }
    }

}