package com.itis.adaptiveplayerapp.di.component

import com.itis.adaptiveplayerapp.bl.MusicRecommender
import com.itis.adaptiveplayerapp.di.module.InjectForMusicRecommendationModule
import dagger.Component

@Component(modules = [InjectForMusicRecommendationModule::class])
interface InjectForMusicRecommenderComponent {
    fun inject(mr: MusicRecommender)
}