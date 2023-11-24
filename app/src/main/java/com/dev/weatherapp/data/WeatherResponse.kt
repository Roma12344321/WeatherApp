package com.dev.weatherapp.data

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("location")
    val location: Location?,
    @SerializedName("current")
    val temperature: Temperature?,
    @SerializedName("forecast")
    val listOfDayResponse: ListOfDayResponse?
)