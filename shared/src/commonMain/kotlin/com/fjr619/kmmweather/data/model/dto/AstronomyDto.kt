package com.fjr619.kmmweather.data.model.dto

import com.fjr619.kmmweather.domain.model.Astronomy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AstronomyDto(
    @SerialName("sunrise") val sunriseTime: String,
    @SerialName("sunset") val sunsetTime: String,
    @SerialName("moonrise") val moonriseTime: String,
    @SerialName("moonset") val moonsetTime: String,
    @SerialName("moon_phase") val moonPhase: String,
    @SerialName("moon_illumination") val moonIllumination: Int,
    @SerialName("is_moon_up") val isMoonUp: Int,
    @SerialName("is_sun_up") val isSunUp: Int,
)

internal fun AstronomyDto.toDomain() = Astronomy(
    sunriseTime = sunriseTime,
    sunsetTime = sunsetTime,
    moonriseTime = moonriseTime,
    moonsetTime = moonsetTime,
    moonPhase = moonPhase,
    moonIllumination = moonIllumination,
    isMoonUp = isMoonUp,
    isSunUp = isSunUp,
)