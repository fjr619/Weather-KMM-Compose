package com.fjr619.kmmweather.data.remote.request

import io.ktor.resources.Resource
import kotlinx.serialization.SerialName


@Resource("current.json")
data class CurrentWeatherRequest (
    @SerialName("q") val query: String,
    @SerialName("aqi") val airQuality: String = "no",
)