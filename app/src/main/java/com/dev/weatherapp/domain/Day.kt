package com.dev.weatherapp.domain


data class Day(
    val data : String?,
    val averageTemperature: Double?,
    val textOfCondition : String?,
    val iconOfCondition : String?
)