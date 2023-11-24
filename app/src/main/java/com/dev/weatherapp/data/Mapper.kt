package com.dev.weatherapp.data

import com.dev.weatherapp.domain.Day

class Mapper {

    private fun mapDayResponseToDay(dayResponse: DayResponse): Day {
        return Day(
            data = dayResponse.date,
            averageTemperature = dayResponse.day?.averageTemperature,
            textOfCondition = dayResponse.day?.condition?.text,
            iconOfCondition = dayResponse.day?.condition?.icon
        )
    }

    fun weatherResponseToListDay(weatherResponse: WeatherResponse): List<Day>? {
        return weatherResponse.listOfDayResponse?.listOfDayResponse?.map {
            mapDayResponseToDay(it)
        }
    }
}