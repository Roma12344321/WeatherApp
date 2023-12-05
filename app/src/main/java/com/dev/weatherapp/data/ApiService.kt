package com.dev.weatherapp.data

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("forecast.json?key=45aaaa571eb647589ad130651230512&days=7")
    suspend fun loadTemperatures(@Query("q") city : String) : WeatherResponse
}