package com.dev.weatherapp.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev.weatherapp.data.DayListRepositoryImpl
import com.dev.weatherapp.domain.Day
import com.dev.weatherapp.domain.Hour
import com.dev.weatherapp.domain.LoadHourForDayUseCase
import com.dev.weatherapp.domain.LoadTemperatureForDayUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel : ViewModel() {

    private val repository = DayListRepositoryImpl
    private val loadTemperatureForDayUseCase = LoadTemperatureForDayUseCase(repository)
    private val loadHourForDayUseCase = LoadHourForDayUseCase(repository)

    private val scope = CoroutineScope(Dispatchers.Main)

    private val _dayTemperature = MutableLiveData<List<Day>>()
    val dayTemperature: LiveData<List<Day>>
        get() = _dayTemperature

    private val _hourTemperature = MutableLiveData<List<Hour>>()
    val hourTemperature: LiveData<List<Hour>>
        get() = _hourTemperature

    fun loadTemperature(city: String) {
        scope.launch {
            _dayTemperature.value = loadTemperatureForDayUseCase.loadTemperature(city)
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