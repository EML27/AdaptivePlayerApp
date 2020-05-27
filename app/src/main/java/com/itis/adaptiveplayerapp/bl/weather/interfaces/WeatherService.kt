package com.itis.adaptiveplayerapp.bl.weather.interfaces

import com.itis.adaptiveplayerapp.bl.weather.response.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather")
    suspend fun weatherByName(@Query("q") name: String): WeatherResponse

    @GET("weather")
    suspend fun weatherByGeo(
        @Query("lon") lon: Double,
        @Query("lat") lat: Double
    ): WeatherResponse

    @GET("weather")
    suspend fun weatherById(@Query("id") id: Int): WeatherResponse
}