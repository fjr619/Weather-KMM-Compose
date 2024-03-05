package com.fjr619.kmmweather.data.model.dto

import com.fjr619.kmmweather.domain.model.Location
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    @SerialName("name") val name: String,
    @SerialName("region") val region: String,
    @SerialName("country") val country: String,
    @SerialName("lat") val latitude: Double,
    @SerialName("lon") val longitude: Double,
    @SerialName("tz_id") val timeZoneId: String,
    @SerialName("localtime_epoch") val localTimeEpoch: Long,
    @SerialName("localtime") val localTime: String,
)

internal fun LocationDto.toDomain() = Location(
    name = name,
    region = region,
    country = country,
    latitude = latitude,
    longitude = longitude,
    timeZoneId = timeZoneId,
    localTimeEpoch = localTimeEpoch,
    localTime = localTime,
)