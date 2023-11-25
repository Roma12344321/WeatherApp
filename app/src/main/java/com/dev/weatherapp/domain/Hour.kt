package com.dev.weatherapp.domain

data class Hour(
    val time : String?,
    val temperature : Double?,
    val textOfHourConditions : String?,
    val iconOfHourConditions : String?
)