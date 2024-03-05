package com.fjr619.kmmweather.data.model.dto

import com.fjr619.kmmweather.domain.model.ForecastDay
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDayDto(
    @SerialName("date") val date: String,
    @SerialName("date_epoch") val dateEpoch: Long,
    @SerialName("day") val day: DayDto,
    @SerialName("astro") val astronomy: AstronomyDto,
    @SerialName("hour") val hours: List<HourDto>,
)

internal fun ForecastDayDto.toDomain() = ForecastDay(
    date = date,
    dateEpoch = dateEpoch,
    day = day.toDomain(),
    astronomy = astronomy.toDomain(),
    hours = hours.map { it.toDomain() },
)