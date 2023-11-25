package com.dev.weatherapp.data

import com.google.gson.annotations.SerializedName

data class DayResponse(
    @SerializedName("date")
    val date : String?,
    @SerializedName("day")
    val day : Day?,
    @SerializedName("hour")
    val listOfHours : List<HourResponse>?
)