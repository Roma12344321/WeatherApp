package com.dev.weatherapp.di

import com.dev.weatherapp.presentation.fragments.DayListFragment
import com.dev.weatherapp.presentation.fragments.HourListFragment
import dagger.Component

@Component(modules = [DataModule::class,ViewModelModule::class])
interface ApplicationComponent {
    fun inject(fragment : DayListFragment)
    fun inject(fragment : HourListFragment)
}