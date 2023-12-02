package com.dev.weatherapp.domain

import javax.inject.Inject

class LoadCurrentWeatherUseCase @Inject constructor(private val repository: DayListRepository) {
    suspend fun loadCurrentWeather(city : String) : Current {
        return repository.loadCurrentWeather(city)
    }
}