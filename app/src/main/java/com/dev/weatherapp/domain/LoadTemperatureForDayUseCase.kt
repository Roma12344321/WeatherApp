package com.dev.weatherapp.domain

import javax.inject.Inject

class LoadTemperatureForDayUseCase @Inject constructor(private val repository: DayListRepository) {

    suspend fun loadTemperature(city:String): List<Day>? {
        return repository.loadTemperatureForeDays(city)
    }
}