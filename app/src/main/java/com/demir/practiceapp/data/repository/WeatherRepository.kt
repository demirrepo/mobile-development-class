package com.demir.practiceapp.data.repository

import com.demir.practiceapp.data.api.WeatherApi
import com.demir.practiceapp.data.model.Weather

class WeatherRepository(
    private val api: WeatherApi
) {
    suspend fun getWeather(city: String): Result<Weather> {
        return try {
            Result.success(api.fetchWeather(city))
        } catch (e: IllegalArgumentException) {
            Result.failure(e)
        }
    }
}