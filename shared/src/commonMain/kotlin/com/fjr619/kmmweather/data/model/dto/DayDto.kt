package com.fjr619.kmmweather.data.model.dto

import com.fjr619.kmmweather.domain.model.Day
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DayDto(
    @SerialName("maxtemp_c") val maxTemperatureInCelsius: Double,
    @SerialName("maxtemp_f") val maxTemperatureInFahrenheit: Double,
    @SerialName("mintemp_c") val minTemperatureInCelsius: Double,
    @SerialName("mintemp_f") val minTemperatureInFahrenheit: Double,
    @SerialName("avgtemp_c") val averageTemperatureInCelsius: Double,
    @SerialName("avgtemp_f") val averageTemperatureInFahrenheit: Double,
    @SerialName("maxwind_mph") val maxWindSpeedMph: Double,
    @SerialName("maxwind_kph") val maxWindSpeedKph: Double,
    @SerialName("totalprecip_mm") val totalPrecipitationMm: Double,
    @SerialName("totalprecip_in") val totalPrecipitationInches: Double,
    @SerialName("totalsnow_cm") val totalSnowCm: Double,
    @SerialName("avgvis_km") val visibilityKm: Double,
    @SerialName("avgvis_miles") val visibilityMiles: Double,
    @SerialName("avghumidity") val humidity: Double,
    @SerialName("daily_will_it_rain") val willItRain: Int,
    @SerialName("daily_chance_of_rain") val chanceOfRain: Int,
    @SerialName("daily_will_it_snow") val willItSnow: Int,
    @SerialName("daily_chance_of_snow") val chanceOfSnow: Int,
    @SerialName("condition") val condition: ConditionDto,
    @SerialName("uv") val uvIndex: Double,
    @SerialName("air_quality") val airQuality: AirQualityDto? = null,
)

internal fun DayDto.toDomain() = Day(
    maxTemperatureInCelsius = maxTemperatureInCelsius,
    maxTemperatureInFahrenheit = maxTemperatureInFahrenheit,
    minTemperatureInCelsius = minTemperatureInCelsius,
    minTemperatureInFahrenheit = minTemperatureInFahrenheit,
    averageTemperatureInCelsius = averageTemperatureInCelsius,
    averageTemperatureInFahrenheit = averageTemperatureInFahrenheit,
    maxWindSpeedMph = maxWindSpeedMph,
    maxWindSpeedKph = maxWindSpeedKph,
    totalPrecipitationMm = totalPrecipitationMm,
    totalPrecipitationInches = totalPrecipitationInches,
    totalSnowCm = totalSnowCm,
    visibilityKm = visibilityKm,
    visibilityMiles = visibilityMiles,
    humidity = humidity,
    willItRain = willItRain,
    chanceOfRain = chanceOfRain,
    willItSnow = willItSnow,
    chanceOfSnow = chanceOfSnow,
    condition = condition.toDomain(),
    uvIndex = uvIndex,
    airQuality = airQuality?.toDomain(),
)