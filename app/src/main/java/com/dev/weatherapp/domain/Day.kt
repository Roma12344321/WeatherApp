package com.dev.weatherapp.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Day(
    val data : String?,
    val averageTemperature: Double?,
    val textOfCondition : String?,
    val iconOfCondition : String?,
    val hourList : List<Hour>?
) : Parcelable