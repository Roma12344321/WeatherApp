package com.dev.weatherapp.data

import com.google.gson.annotations.SerializedName

data class Current(
    @SerializedName("last_updated")
    val time : String?,
    @SerializedName("temp_c")
    val temp : Double?,
    @SerializedName("condition")
    val conditionOfCurrentDay: ConditionOfCurrentDay?

)
