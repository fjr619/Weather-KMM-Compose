package com.fjr619.kmmweather.domain.model

data class Astronomy(
    val sunriseTime: String,
    val sunsetTime: String,
    val moonriseTime: String,
    val moonsetTime: String,
    val moonPhase: String,
    val moonIllumination: Int,
    val isMoonUp: Int,
    val isSunUp: Int,
)