package com.dev.weatherapp.data

import com.dev.weatherapp.domain.Current
import com.dev.weatherapp.domain.Day
import com.dev.weatherapp.domain.DayListRepository
import com.dev.weatherapp.domain.Hour
import javax.inject.Inject

class DayListRepositoryImpl @Inject constructor(
    private val mapper : Mapper,
    private val apiService: ApiService
) : DayListRepository {


    override suspend fun loadTemperatureForeDays(city:String): List<Day>? {
        return mapper.weatherResponseToListDay(apiService.loadTemperatures(city))
    }

    override fun loadHoursForDay(day: Day): List<Hour>? {
        return day.hourList
    }

    override suspend fun loadCurrentWeather(city: String): Current {
        return mapper.mapWeatherResponseToCurrent(apiService.loadTemperatures(city))
    }
}