package com.fjr619.kmmweather.data.remote.request

import io.ktor.resources.Resource
import kotlinx.serialization.SerialName

@Resource("forecast.json")
data class ForecastRequest(
    @SerialName("q") val query: String,
    @SerialName("aqi") val airQuality: String = "no",
    @SerialName("days") val days: Int = 3,
    @SerialName("alerts") val alerts: String = "yes",
)