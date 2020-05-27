package com.itis.adaptiveplayerapp.bl

import com.itis.adaptiveplayerapp.bl.dto.StateDto
import com.itis.adaptiveplayerapp.bl.gps.UserOccupation
import com.itis.adaptiveplayerapp.bl.time.DayTime
import com.itis.adaptiveplayerapp.bl.weather.ApiFactory
import com.itis.adaptiveplayerapp.di.component.DaggerInjectForStateReturnerComponent
import com.itis.adaptiveplayerapp.di.component.DaggerLocationRepresenterComponent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StateReturner @Inject constructor() {

    init {
        DaggerInjectForStateReturnerComponent.create().inject(this)
    }

    @Inject
    lateinit var userOccupation: UserOccupation

    @Inject
    lateinit var dayTime: DayTime

    @Inject
    lateinit var apiFactory: ApiFactory

    fun getOccupation() =
        userOccupation.getCurrentOccupation()


    fun getDayTime() = dayTime.getTimeOfDay()

    suspend fun getWeather(): String {
        val location = userOccupation.getLocation()
        val weather = apiFactory.weatherService.weatherByGeo(location.longitude, location.latitude)
        return weather.weather[0].main
    }

    suspend fun getState(): StateDto {
        return StateDto(occupation = getOccupation(), time = getDayTime(), weather = getWeather())
    }
}
