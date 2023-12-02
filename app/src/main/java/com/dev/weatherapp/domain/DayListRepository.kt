package com.dev.weatherapp.domain

interface DayListRepository {
    suspend fun loadTemperatureForeDays(city:String) : List<Day>?
    fun loadHoursForDay(day: Day) : List<Hour>?
    suspend fun loadCurrentWeather(city: String) : Current
}