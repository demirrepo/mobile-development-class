package com.demir.practiceapp.mvi.model



sealed interface WeatherIntent
{
    data class SelectCity(val city: String) : WeatherIntent
    data object DismissError : WeatherIntent
}