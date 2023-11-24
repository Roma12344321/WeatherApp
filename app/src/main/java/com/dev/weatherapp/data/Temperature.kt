package com.dev.weatherapp.data

import com.google.gson.annotations.SerializedName

data class Temperature(
    @SerializedName("temp_c")
    val temp : Double?,
    @SerializedName("condition")
    val conditionOfCurrentDay: ConditionOfCurrentDay?

)
