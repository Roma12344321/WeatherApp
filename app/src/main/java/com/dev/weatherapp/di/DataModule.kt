package com.dev.weatherapp.di

import com.dev.weatherapp.data.ApiFactory
import com.dev.weatherapp.data.ApiService
import com.dev.weatherapp.data.DayListRepositoryImpl
import com.dev.weatherapp.domain.DayListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface DataModule {
    @Singleton
    @Binds
    fun bindDayListRepository(impl: DayListRepositoryImpl): DayListRepository

    companion object {
        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}