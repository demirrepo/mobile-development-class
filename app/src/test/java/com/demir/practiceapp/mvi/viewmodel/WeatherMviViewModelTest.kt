package com.demir.practiceapp.mvi.viewmodel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import com.demir.practiceapp.data.api.FakeWeatherApi
import com.demir.practiceapp.data.repository.WeatherRepository
import com.demir.practiceapp.mvi.model.WeatherIntent
import com.demir.practiceapp.mvi.viewmodel.WeatherMviViewModel

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherMviViewModelTest
{
    @Before
    fun setUp()
    {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @After
    fun tearDown()
    {
        Dispatchers.resetMain()
    }

    private fun newViewModel() =
        WeatherMviViewModel(WeatherRepository(FakeWeatherApi(latencyMs = 0)))

    @Test
    fun selecting_a_known_city_ends_with_weather_and_no_loading()
    {
        val viewModel = newViewModel()
        viewModel.onIntent(WeatherIntent.SelectCity("Bukhara"))
        val state = viewModel.state.value
        assertFalse(state.isLoading)
        assertEquals("Bukhara", state.weather?.city)
        assertNull(state.error)
    }

    @Test
    fun selecting_an_unknown_city_ends_with_error()
    {
        val viewModel = newViewModel()
        viewModel.onIntent(WeatherIntent.SelectCity("Atlantis"))
        val state = viewModel.state.value
        assertFalse(state.isLoading)
        assertNull(state.weather)
        assertEquals("No forecast for Atlantis", state.error)
    }

    @Test
    fun dismissing_the_error_clears_it_and_keeps_the_old_weather()
    {
        val viewModel = newViewModel()
        viewModel.onIntent(WeatherIntent.SelectCity("Tashkent"))
        val oldWeather = viewModel.state.value.weather
        viewModel.onIntent(WeatherIntent.SelectCity("Atlantis"))
        val errorState = viewModel.state.value
        assertEquals("No forecast for Atlantis", errorState.error)
        assertEquals(oldWeather, errorState.weather)

        viewModel.onIntent(WeatherIntent.DismissError)

        assertEquals(errorState.copy(error = null), viewModel.state.value)
        assertEquals("No forecast for Atlantis", errorState.error)
    }
}