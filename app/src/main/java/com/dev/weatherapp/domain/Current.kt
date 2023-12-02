package com.dev.weatherapp.domain

data class Current(
    val time : String?,
    val temp : Double?,
    val textCondition : String?,
    val iconCondition : String?
)