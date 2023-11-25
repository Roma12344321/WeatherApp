package com.dev.weatherapp.data

import com.dev.weatherapp.domain.Day
import com.dev.weatherapp.domain.DayListRepository
import com.dev.weatherapp.domain.Hour

object DayListRepositoryImpl : DayListRepository {

    private val apiService = ApiFactory.apiService
    private val mapper = Mapper()

    override suspend fun loadTemperatureForeDays(city:String): List<Day>? {
        return mapper.weatherResponseToListDay(apiService.loadTemperatures(city))
    }

    override fun loadHoursForDay(day: Day): List<Hour>? {
        return day.hourList
    }
}