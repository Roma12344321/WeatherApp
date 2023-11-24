package com.dev.weatherapp.data

import com.google.gson.annotations.SerializedName

data class ConditionOfCurrentDay(
    @SerializedName("text")
    val text : String?,
    @SerializedName("icon")
    val icon : String?
)
