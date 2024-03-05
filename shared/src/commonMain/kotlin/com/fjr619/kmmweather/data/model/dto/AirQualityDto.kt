package com.fjr619.kmmweather.data.model.dto

import com.fjr619.kmmweather.domain.model.AirQuality
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AirQualityDto(
    @SerialName("co") val carbonMonoxide: Double? = null,
    @SerialName("no2") val nitrogenDioxide: Double? = null,
    @SerialName("o3") val ozone: Double? = null,
    @SerialName("so2") val sulphurDioxide: Double? = null,
    @SerialName("pm2_5") val pm2_5: Double? = null,
    @SerialName("pm10") val pm10: Double? = null,
    @SerialName("us-epa-index") val usEpaIndex: Int? = null,
    @SerialName("gb-defra-index") val gbDefraIndex: Int? = null,
)

internal fun AirQualityDto.toDomain() = AirQuality(
    carbonMonoxide = carbonMonoxide ?: 0.0,
    nitrogenDioxide = nitrogenDioxide ?: 0.0,
    ozone = ozone ?: 0.0,
    sulphurDioxide = sulphurDioxide ?: 0.0,
    pm2_5 = pm2_5 ?: 0.0,
    pm10 = pm10 ?: 0.0,
    usEpaIndex = usEpaIndex ?: 0,
    gbDefraIndex = gbDefraIndex ?: 0,
)