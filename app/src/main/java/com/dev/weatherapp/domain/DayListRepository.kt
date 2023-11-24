package com.dev.weatherapp.domain

interface DayListRepository {
    suspend fun loadTemperatureForeDays(city:String) : List<Day>?
}