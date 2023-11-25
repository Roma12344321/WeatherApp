package com.dev.weatherapp.data

import com.google.gson.annotations.SerializedName

data class HourResponse(
    @SerializedName("time")
    val time : String?,
    @SerializedName("temp_c")
    val temperature : Double?,
    @SerializedName("condition")
    val hourCondition: HourCondition?
)