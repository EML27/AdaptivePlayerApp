package com.itis.adaptiveplayerapp.di.component

import android.content.Context
import com.itis.adaptiveplayerapp.di.module.InjectForSpotyServiceModule
import com.itis.adaptiveplayerapp.service.SpotifyPlayerService

import dagger.Component

@Component(modules = [InjectForSpotyServiceModule::class])
public interface InjectForSpotyServiceComponent {
    fun  inject(obj: SpotifyPlayerService)
}
