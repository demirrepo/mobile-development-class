package com.demir.practiceapp.mvi.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.demir.practiceapp.di.WeatherModule
import com.demir.practiceapp.mvi.model.WeatherIntent
import com.demir.practiceapp.mvi.model.WeatherState
import com.demir.practiceapp.mvi.viewmodel.WeatherMviViewModel
import com.demir.practiceapp.ui.WeatherScreen

class MviActivity : ComponentActivity() {

    private val viewModel: WeatherMviViewModel by viewModels {
        WeatherModule.weatherMviViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val state by viewModel.state.collectAsStateWithLifecycle()
                WeatherScreen(
                    status = state.toStatusText(),
                    onCitySelected = { city ->
                        viewModel.onIntent(WeatherIntent.SelectCity(city))
                    },
                )
            }
        }
    }



    private fun WeatherState.toStatusText(): String = when
    {
        isLoading -> "Loading…"
        error != null -> "Error: $error"
        weather != null -> "${weather.city}: ${weather.temperatureC}°C, ${weather.condition}"
        else -> "Pick a city"
    }}