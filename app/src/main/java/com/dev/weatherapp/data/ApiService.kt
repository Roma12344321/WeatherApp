package com.dev.weatherapp.data

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("forecast.json?key=ce5eade850a34b0097e232311232111&days=7")
    suspend fun loadTemperatures(@Query("q") city : String) : WeatherResponse
}