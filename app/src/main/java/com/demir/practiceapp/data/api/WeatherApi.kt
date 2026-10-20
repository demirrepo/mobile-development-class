package com.demir.practiceapp.data.api

import com.demir.practiceapp.data.model.Weather

interface WeatherApi {
    suspend fun fetchWeather(city: String): Weather
}