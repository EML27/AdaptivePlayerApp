package com.itis.adaptiveplayerapp.di.module

import com.itis.adaptiveplayerapp.bl.gps.UserOccupation
import com.itis.adaptiveplayerapp.bl.time.DayTime
import com.itis.adaptiveplayerapp.bl.weather.ApiFactory
import dagger.Module
import dagger.Provides

@Module
class InjectForStateReturnerModule {
    @Provides
    fun getUserOccupation(): UserOccupation {
        return UserOccupation()
    }

    @Provides
    fun getDayTime(): DayTime {
        return DayTime()
    }

    @Provides
    fun getApiFactory(): ApiFactory {
        return ApiFactory()
    }
}