package com.itis.adaptiveplayerapp.di.module

import com.itis.adaptiveplayerapp.bl.gps.LocationRepresenter
import com.itis.adaptiveplayerapp.bl.gps.UserOccupation
import dagger.Module
import dagger.Provides

@Module
class StateModule {
    @Provides
    fun getLocationRepresenter():LocationRepresenter{
        return LocationRepresenter()
    }

    @Provides
    fun getUserOccupation(): UserOccupation{
        return UserOccupation()
    }
}