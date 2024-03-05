package com.fjr619.kmmweather.domain.model

data class CurrentWeather(
    val location: Location,
    val currentWeather: Weather,
)