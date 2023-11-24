package com.dev.weatherapp.data

import com.google.gson.annotations.SerializedName

data class Day(
    @SerializedName("avgtemp_c")
    val averageTemperature: Double?,
    @SerializedName("condition")
    val condition: Condition?
)