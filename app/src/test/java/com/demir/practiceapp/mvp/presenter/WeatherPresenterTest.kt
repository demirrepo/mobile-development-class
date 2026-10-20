package com.demir.practiceapp.mvp.presenter

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.junit.Test
import com.demir.practiceapp.data.api.FakeWeatherApi
import com.demir.practiceapp.data.model.Weather
import com.demir.practiceapp.data.repository.WeatherRepository
import com.demir.practiceapp.mvp.WeatherContract

class FakeView : WeatherContract.View {
    val events = mutableListOf<String>()

    override fun showLoading() { events += "loading" }
    override fun showWeather(weather: Weather) { events += "weather:${weather.city}" }
    override fun showError(message: String) { events += "error:$message" }
}

class WeatherPresenterTest {

    private val view = FakeView()
    private val presenter = WeatherPresenter(
        view = view,
        repository = WeatherRepository(FakeWeatherApi(latencyMs = 0)),
        // Unconfined: the coroutine runs right away, on the test thread
        scope = CoroutineScope(Dispatchers.Unconfined),
    )

    @Test
    fun known_city_shows_loading_then_weather() {
        presenter.onCitySelected("Tashkent")
        assertEquals(listOf("loading", "weather:Tashkent"), view.events)
    }

    // TODO: unknown_city_shows_loading_then_error()  ("Atlantis")
    // TODO: two_requests_in_a_row_show_two_loading_events()
}