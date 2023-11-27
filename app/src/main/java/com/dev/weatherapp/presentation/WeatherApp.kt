package com.dev.weatherapp.presentation

import android.app.Application
import com.dev.weatherapp.di.DaggerApplicationComponent

class WeatherApp : Application() {
    val component by lazy {
        DaggerApplicationComponent.create()
    }
}