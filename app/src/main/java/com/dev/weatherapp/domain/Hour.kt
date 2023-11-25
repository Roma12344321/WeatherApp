package com.dev.weatherapp.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hour(
    val time : String?,
    val temperature : Double?,
    val textOfHourConditions : String?,
    val iconOfHourConditions : String?
) : Parcelable