package com.demir.practiceapp.mvp.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.demir.practiceapp.data.model.Weather
import com.demir.practiceapp.di.WeatherModule
import com.demir.practiceapp.mvp.WeatherContract
import com.demir.practiceapp.mvp.presenter.WeatherPresenter
import com.demir.practiceapp.ui.WeatherScreen

class MvpActivity : ComponentActivity(), WeatherContract.View {

    private var status by mutableStateOf("Pick a city")
    private val presenter = WeatherPresenter(
        view = this,
        repository = WeatherModule.repository,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MVP", "onCreate   activity=${hashCode()}")
        setContent {
            MaterialTheme {
                WeatherScreen(status = status, onCitySelected = presenter::onCitySelected)
            }
        }
    }

//    override fun onDestroy() {
//        Log.d("MVP", "onDestroy  activity=${hashCode()}")
//        super.onDestroy()
//    }

    // MvpActivity
    override fun onDestroy()
    {
        presenter.destroy()
        Log.d("MVP", "onDestroy  activity=${hashCode()}")
        super.onDestroy()
    }

    override fun showLoading() { status = "Loading…" }

    override fun showWeather(weather: Weather) {
        Log.d("MVP", "showWeather called on activity=${hashCode()}")
        status = "${weather.city}: ${weather.temperatureC}°C, ${weather.condition}"
    }

    override fun showError(message: String) { status = "Error: $message" }
}