package com.demir.practiceapp.data.api

import kotlinx.coroutines.delay
import com.demir.practiceapp.data.model.Weather

class FakeWeatherApi(
    private val latencyMs: Long = 1500L
) : WeatherApi {

    private val forecasts: Map<String, Weather> = mapOf(
        "Tashkent" to Weather("Tashkent", 24, "Sunny"),
        "Samarkand" to Weather("Samarkand", 21, "Cloudy"),
        "Bukhara" to Weather("Bukhara", 27, "Windy")
    )

    override suspend fun fetchWeather(city: String): Weather {
        delay(latencyMs)
        return forecasts[city]
            ?: throw IllegalArgumentException("No forecast for $city")
    }
}