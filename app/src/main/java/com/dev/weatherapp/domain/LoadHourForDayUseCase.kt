package com.dev.weatherapp.domain

import javax.inject.Inject

class LoadHourForDayUseCase @Inject constructor(private val dayListRepository: DayListRepository) {
    fun loadHoursForDay(day: Day): List<Hour>? {
        return dayListRepository.loadHoursForDay(day)
    }
}