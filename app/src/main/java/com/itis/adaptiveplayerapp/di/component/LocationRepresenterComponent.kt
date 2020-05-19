package com.itis.adaptiveplayerapp.di.component

import com.itis.adaptiveplayerapp.bl.gps.UserOccupation
import com.itis.adaptiveplayerapp.di.module.LocationRepresenterModule
import dagger.Component

@Component(modules = [LocationRepresenterModule::class])
interface LocationRepresenterComponent {
    fun inject(obj: UserOccupation)
}