package com.dev.weatherapp.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev.weatherapp.domain.Day
import com.dev.weatherapp.domain.Hour
import com.dev.weatherapp.domain.LoadHourForDayUseCase
import com.dev.weatherapp.domain.LoadTemperatureForDayUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val loadTemperatureForDayUseCase: LoadTemperatureForDayUseCase,
    private val loadHourForDayUseCase: LoadHourForDayUseCase

) : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.Main)

    private val _dayTemperature = MutableLiveData<List<Day>>()
    val dayTemperature: LiveData<List<Day>>
        get() = _dayTemperature

    private val _hourTemperature = MutableLiveData<List<Hour>>()
    val hourTemperature: LiveData<List<Hour>>
        get() = _hourTemperature

    private val _isLoaded = MutableLiveData<Boolean>()
    val isLoaded : LiveData<Boolean>
        get() = _isLoaded

    fun loadTemperature(city: String) {
        _isLoaded.value = false

        scope.launch {
            try {
                _dayTemperature.value = loadTemperatureForDayUseCase.loadTemperature(city)
                _isLoaded.value = true
            } catch (_: Exception){
                _isLoaded.value = false
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