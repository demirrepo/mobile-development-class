package com.demir.practiceapp.mvc.controller

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import com.demir.practiceapp.mvc.model.WeatherModel
import com.demir.practiceapp.mvc.view.WeatherView

class WeatherController(
    private val model: WeatherModel,
    private val view: WeatherView,
    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main),
)
{
    fun onCitySelected(city: String)
    {
        scope.launch()
        {
            model.startLoading()
            view.render()
            model.load(city)
            view.render()
        }
    }
}