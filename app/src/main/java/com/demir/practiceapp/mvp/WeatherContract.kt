package com.demir.practiceapp.mvp

import com.demir.practiceapp.data.model.Weather

interface WeatherContract {
    interface View {
        fun showLoading()
        fun showWeather(weather: Weather)
        fun showError(message: String)
    }
}