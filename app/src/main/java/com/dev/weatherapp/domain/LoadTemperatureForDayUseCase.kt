package com.dev.weatherapp.domain

class LoadTemperatureForDayUseCase(private val repository: DayListRepository) {

    suspend fun loadTemperature(city:String): List<Day>? {
        return repository.loadTemperatureForeDays(city)
    }
}