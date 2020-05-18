package com.itis.adaptiveplayerapp.di.module

import com.itis.adaptiveplayerapp.bl.MusicRecommender
import dagger.Module
import dagger.Provides

@Module
class MusicRecommenderModule {
    @Provides
    fun getMusicRecommender():MusicRecommender{
        return MusicRecommender()
    }
}