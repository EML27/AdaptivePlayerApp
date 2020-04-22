package com.itis.weather_talker.interfaces
import com.itis.weather_talker.response.WeatherList
import com.itis.weather_talker.response.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather")
    suspend fun weatherByName(@Query("q") name: String): WeatherResponse

    @GET("find")
    suspend fun weatherInNearbyCities(@Query("lon") lon: Double, @Query("lat") lat: Double, @Query("cnt") cnt: Int): Response<WeatherList>

    @GET("weather")
    suspend fun weatherById(@Query("id") id: Int): WeatherResponse
}
