package com.itis.weather_talker.response

import com.google.gson.annotations.SerializedName

data class WeatherList(@SerializedName("list") var list: List<WeatherResponse>)
