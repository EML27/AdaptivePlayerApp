package com.itis.adaptiveplayerapp.di.component

import android.content.Context
import com.itis.adaptiveplayerapp.di.module.MusicRecommenderModule

import dagger.Component

@Component(modules = [MusicRecommenderModule::class])
public interface MusicRecommenderComponent {
    fun  inject(obj: Context)
}
