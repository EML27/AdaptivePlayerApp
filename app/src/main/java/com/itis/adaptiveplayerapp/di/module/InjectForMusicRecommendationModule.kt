package com.itis.adaptiveplayerapp.di.module

import com.itis.adaptiveplayerapp.App
import com.itis.adaptiveplayerapp.bl.room.database.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class InjectForMusicRecommendationModule {
    @Provides
    fun getDataBase(): AppDatabase {
        return App.getDb()
    }
}