package com.demir.practiceapp.mvi.model

import com.demir.practiceapp.data.model.Weather

data class WeatherState(
    val isLoading: Boolean = false,
    val weather: Weather? = null,
    val error: String? = null,
)

