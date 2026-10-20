package com.demir.practiceapp.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.demir.practiceapp.data.api.FakeWeatherApi
import com.demir.practiceapp.data.repository.WeatherRepository
import com.demir.practiceapp.mvi.viewmodel.WeatherMviViewModel
import com.demir.practiceapp.mvvm.viewmodel.WeatherViewModel

object WeatherModule
{
    val repository = WeatherRepository(FakeWeatherApi(latencyMs = 3_000))

    val weatherViewModelFactory: ViewModelProvider.Factory = viewModelFactory()
    {
        initializer()
        {
            WeatherViewModel(repository)
        }
    }

    val weatherMviViewModelFactory: ViewModelProvider.Factory = viewModelFactory()
    {
        initializer()
        {
            WeatherMviViewModel(repository)
        }
    }
}


