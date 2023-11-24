package com.dev.weatherapp.data

import com.google.gson.annotations.SerializedName

data class ListOfDayResponse(
    @SerializedName("forecastday")
    val listOfDayResponse: List<DayResponse>?
)