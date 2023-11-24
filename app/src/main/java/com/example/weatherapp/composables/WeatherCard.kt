package com.example.weatherapp.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherapp.helpers.WeatherHelper

@Composable
fun WeatherCard(city: String) {
    // Simulated weather data
    val weatherData = WeatherHelper.generateWeatherData()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        // Current Weather
        CurrentWeatherCard(weatherData.currentWeather)

        // Upcoming Days
        UpcomingDays(weatherData.upcomingDays)
    }
}