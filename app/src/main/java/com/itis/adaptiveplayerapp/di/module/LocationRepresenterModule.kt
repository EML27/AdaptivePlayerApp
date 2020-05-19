package com.itis.adaptiveplayerapp.di.module

import com.itis.adaptiveplayerapp.bl.gps.LocationRepresenter
import com.itis.adaptiveplayerapp.bl.gps.UserOccupation
import dagger.Module
import dagger.Provides

@Module
class LocationRepresenterModule {
    @Provides
    fun getLocationRepresenter():LocationRepresenter{
        return LocationRepresenter()
    }

}