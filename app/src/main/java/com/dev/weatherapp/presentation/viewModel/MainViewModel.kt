package com.dev.weatherapp.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev.weatherapp.domain.Current
import com.dev.weatherapp.domain.Day
import com.dev.weatherapp.domain.Hour
import com.dev.weatherapp.domain.LoadCurrentWeatherUseCase
import com.dev.weatherapp.domain.LoadHourForDayUseCase
import com.dev.weatherapp.domain.LoadTemperatureForDayUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val loadTemperatureForDayUseCase: LoadTemperatureForDayUseCase,
    private val loadHourForDayUseCase: LoadHourForDayUseCase,
    private val loadCurrentWeatherUseCase: LoadCurrentWeatherUseCase
) : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.Main)

    private val _dayTemperature = MutableLiveData<List<Day>>()
    val dayTemperature: LiveData<List<Day>>
        get() = _dayTemperature

    private val _currentWeather = MutableLiveData<Current>()
    val currentWeather: LiveData<Current>
        get() = _currentWeather

    private val _hourTemperature = MutableLiveData<List<Hour>>()
    val hourTemperature: LiveData<List<Hour>>
        get() = _hourTemperature

    private val _isLoaded = MutableLiveData<Boolean>()
    val isLoaded: LiveData<Boolean>
        get() = _isLoaded

    fun loadTemperature(city: String) {
        _isLoaded.value = false
        scope.launch {
            try {
                _dayTemperature.value = loadTemperatureForDayUseCase.loadTemperature(city)
                _isLoaded.value = true
            } catch (_: Exception) {
                _isLoaded.value = false
            }
        }
    }

    fun loadCurrentWeather(city: String) {
        _isLoaded.value = false
        scope.launch {
            while (true) {
                try {
                    _currentWeather.value = loadCurrentWeatherUseCase.loadCurrentWeather(city)
                    _isLoaded.value = true
                } catch (_: Exception) {
                    _isLoaded.value = false
                }
                Log.d("TAG", "Internet")
                delay(30000)
            }
        }
    }

    fun loadTemperatureForDay(day: Day) {
        _hourTemperature.value = loadHourForDayUseCase.loadHoursForDay(day)
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}