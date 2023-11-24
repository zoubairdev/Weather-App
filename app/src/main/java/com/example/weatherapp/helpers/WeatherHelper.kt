package com.example.weatherapp.helpers

import androidx.compose.runtime.Composable
import com.example.weatherapp.data.WeatherData
import com.example.weatherapp.data.WeatherInfo

class WeatherHelper {

    companion object {
        @Composable
        fun generateWeatherData(): WeatherData {
            // Simulated weather data
            val currentWeather = WeatherInfo(
                weatherState = "Sunny",
                temperature = 25,
                dayOfWeek = "Today"
            )

            val upcomingDays = listOf(
                WeatherInfo(weatherState = "Sunny", temperature = 27, dayOfWeek = "Monday"),
                WeatherInfo(weatherState = "Cloudy", temperature = 23, dayOfWeek = "Tuesday"),
                WeatherInfo(weatherState = "Rainy", temperature = 18, dayOfWeek = "Wednesday"),
                WeatherInfo(
                    weatherState = "Thunderstorm",
                    temperature = 20,
                    dayOfWeek = "Thursday"
                ),
                WeatherInfo(weatherState = "Snowy", temperature = 15, dayOfWeek = "Friday")
            )

            return WeatherData(currentWeather, upcomingDays)
        }
    }
}