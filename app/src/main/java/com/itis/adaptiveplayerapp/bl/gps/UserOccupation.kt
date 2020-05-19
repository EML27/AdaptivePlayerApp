package com.itis.adaptiveplayerapp.bl.gps

import com.itis.adaptiveplayerapp.bl.StateReturner
import com.itis.adaptiveplayerapp.di.component.DaggerLocationRepresenterComponent
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserOccupation @Inject constructor() {
    init {
        DaggerLocationRepresenterComponent.create().inject(this)
    }

    @Inject
    lateinit var locationRepresenter: LocationRepresenter

    fun getCurrentOccupation(): String {
        val speed = locationRepresenter.getCurrentLocation().speed

        var res = when (speed.toInt()) {
            in 0..1 -> Occupation.CALM
            in 1..2 -> Occupation.WALK
            in 2..3 -> Occupation.JOG
            in 3..5 -> Occupation.SPORT
            in 5..22 -> Occupation.CALMTRANSPORT
            in 22..38 -> Occupation.FASTTRANSPORT
            else -> Occupation.UNDEFINED
        }
        return res.name
    }
}

@Module
class UserOccupationModule {
    @Provides
    fun getUserOccupation(): UserOccupation {
        return UserOccupation()
    }
}

@Component(modules = [UserOccupationModule::class])
interface UserOccupationComponent {
    fun inject(obj: StateReturner)
}