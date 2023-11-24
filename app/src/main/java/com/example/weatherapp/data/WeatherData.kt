package com.example.weatherapp.data

data class WeatherData(
    val currentWeather: WeatherInfo,
    val upcomingDays: List<WeatherInfo>
)