package com.itis.adaptiveplayerapp.di.component

import com.itis.adaptiveplayerapp.di.module.MusicRecommenderModule
import com.itis.adaptiveplayerapp.ui.view.MainActivity

import dagger.Component

@Component(modules = [MusicRecommenderModule::class])
public interface MusicRecommenderComponent {
    fun  inject(obj: MainActivity)
}
