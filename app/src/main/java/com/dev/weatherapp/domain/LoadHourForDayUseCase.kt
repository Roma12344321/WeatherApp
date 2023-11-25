package com.dev.weatherapp.domain

class LoadHourForDayUseCase(private val dayListRepository: DayListRepository) {
    fun loadHoursForDay(day: Day): List<Hour>? {
        return dayListRepository.loadHoursForDay(day)
    }
}