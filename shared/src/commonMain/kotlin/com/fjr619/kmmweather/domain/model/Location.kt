package com.fjr619.kmmweather.domain.model

data class Location(
    val name: String,
    val region: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val timeZoneId: String,
    val localTimeEpoch: Long,
    val localTime: String,
)