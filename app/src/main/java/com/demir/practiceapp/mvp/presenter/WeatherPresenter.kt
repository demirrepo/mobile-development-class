package com.demir.practiceapp.mvp.presenter

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import com.demir.practiceapp.data.repository.WeatherRepository
import com.demir.practiceapp.mvp.WeatherContract

class WeatherPresenter(
    private val view: WeatherContract.View,
    private val repository: WeatherRepository,
    private val scope: CoroutineScope =
        CoroutineScope(SupervisorJob() + Dispatchers.Main),
) {

    fun onCitySelected(city: String)
    {
        view.showLoading()
        scope.launch()
        {
            repository.getWeather(city)
                .onSuccess()
                {
                    view.showWeather(it)
                }
                .onFailure()
                {
                    view.showError(it.message ?: "Unknown error")
                }
        }
    }

    fun destroy()
    {
        scope.cancel()
    }

}
