package com.fjr619.kmmweather.domain.model

data class Forecast(
    val location: Location,
    val currentWeather: Weather,
    val forecastDays: List<ForecastDay>,
)