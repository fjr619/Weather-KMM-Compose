package com.fjr619.kmmweather.data.model.dto

import com.fjr619.kmmweather.data.model.dto.ForecastDayDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDto(
    @SerialName("forecastday") val forecastDays: List<ForecastDayDto>,
)