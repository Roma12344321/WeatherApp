package com.dev.weatherapp.data

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("forecast.json?key=bdd356c2ba12463d9bd194228230212&days=7")
    suspend fun loadTemperatures(@Query("q") city : String) : WeatherResponse
}