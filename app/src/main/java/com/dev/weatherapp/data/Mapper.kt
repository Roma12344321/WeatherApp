package com.dev.weatherapp.data

import com.dev.weatherapp.domain.Day
import com.dev.weatherapp.domain.Hour
import javax.inject.Inject

class Mapper @Inject constructor() {

    private fun mapDayResponseToDay(dayResponse: DayResponse): Day {
        return Day(
            data = dayResponse.date,
            averageTemperature = dayResponse.day?.averageTemperature,
            textOfCondition = dayResponse.day?.condition?.text,
            iconOfCondition = dayResponse.day?.condition?.icon,
            hourList = dayResponse.listOfHours?.map {
                mapHourResponseToHour(it)
            }
        )
    }

    private fun mapHourResponseToHour(hourResponse: HourResponse): Hour {
        return Hour(
            time = hourResponse.time,
            temperature = hourResponse.temperature,
            textOfHourConditions = hourResponse.hourCondition?.text,
            iconOfHourConditions = hourResponse.hourCondition?.icon
        )
    }

    fun weatherResponseToListDay(weatherResponse: WeatherResponse): List<Day>? {
        return weatherResponse.listOfDayResponse?.listOfDayResponse?.map {
            mapDayResponseToDay(it)
        }
    }
}