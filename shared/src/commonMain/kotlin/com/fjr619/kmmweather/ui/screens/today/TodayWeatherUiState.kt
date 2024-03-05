package com.fjr619.kmmweather.ui.screens.today

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.fjr619.kmmweather.MR
import com.fjr619.kmmweather.domain.model.Forecast
import com.fjr619.kmmweather.domain.model.ForecastDay
import com.fjr619.kmmweather.domain.model.Hour
import com.fjr619.kmmweather.ui.model.HourUi
import com.fjr619.kmmweather.ui.model.toUi
import dev.icerock.moko.resources.StringResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class TodayWeatherUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val currentTime: String = "",
    val currentTemperature: String = "",
    val feelsLikeTemperature: String = "",
    val condition: String = "",
    val conditionIconUrl: String = "",
    val hourlyForecasts: List<HourUi> = emptyList(),
    val rainChance: String = "",
    val humidity: String = "",
    val dewPoint: String = "",
    val pressure: String = "",
    val uvIndex: String = "",
    val visibility: String = "",
    val totalPrecipitation: String = "",
    val windSpeed: Int = 0,
    val windDirectionDegrees: Int = 0,
    val windDirection: StringResource? = null,
    val sunrise: String = "",
    val sunset: String = "",
    val moonrise: String = "",
    val moonset: String = "",
    ) {
    val windSpeedColor: Color
        get() {
            return getWindColor(windSpeed)
        }

    val windSpeedDescription: StringResource
        get() {
            return when (windSpeed) {
                in 0..20 -> MR.strings.wind_speed_light
                in 21..40 -> MR.strings.wind_speed_moderate
                in 41..60 -> MR.strings.wind_speed_strong
                else -> MR.strings.wind_speed_super_strong
            }
        }
}

internal fun getWindColor(speed: Int) = when (speed) {
    in 0..20 -> Color.Cyan
    in 21..40 -> Color.Green
    in 41..60 -> Color.Yellow
    else -> Color.Red
}

internal fun MutableStateFlow<TodayWeatherUiState>.setResponse(result: Forecast) {
    val allDaysHours = getForecastHours(
        forecastDays = result.forecastDays,
        currentTimeEpochSec = result.location.localTimeEpoch,
        timeZoneId = result.location.timeZoneId,

        )

    update {
        it.copy(
            isError = false,
            isLoading = false,
            currentTime = formatTime(
                currentTime = parseTime(result.location.localTimeEpoch, result.location.timeZoneId)
            ),
            currentTemperature = result.currentWeather.temperatureCelsius.toInt().toString(),
            feelsLikeTemperature = result.currentWeather.feelsLikeTemperatureCelsius.toInt()
                .toString(),
            condition = result.currentWeather.weatherCondition.condition,
            conditionIconUrl = "https:${result.currentWeather.weatherCondition.iconUrl}",
            hourlyForecasts = allDaysHours,
            rainChance = result.forecastDays.first().day.chanceOfRain.toString(),
            humidity = result.currentWeather.humidityPercentage.toInt().toString(),
            dewPoint = allDaysHours.first().dewPoint,
            pressure = result.currentWeather.pressureMillibars.toInt().toString(),
            uvIndex = result.currentWeather.uvIndex.toInt()
                .toString(), // TODO: Add moderate, high, low, etc.
            visibility = result.currentWeather.visibilityKm.toInt().toString(),
            totalPrecipitation = result.forecastDays.first().day.totalPrecipitationMm.toString(),
            windSpeed = allDaysHours.first().windSpeed,
            windDirectionDegrees = allDaysHours.first().windDirectionDegrees,
            windDirection = getWindDirection(allDaysHours.first().windDirection),
            sunrise = result.forecastDays.first().astronomy.sunriseTime.lowercase(),
            sunset = result.forecastDays.first().astronomy.sunsetTime.lowercase(),
            moonrise = result.forecastDays.first().astronomy.moonriseTime.lowercase(),
            moonset = result.forecastDays.first().astronomy.moonsetTime.lowercase(),

        )
    }
}

private fun getWindDirection(windDirection: String): StringResource =
    when (windDirection.lowercase()) {
        "n" -> MR.strings.wind_direction_north
        "nne", "ne", "ene" -> MR.strings.wind_direction_northeast
        "e" -> MR.strings.wind_direction_east
        "ese", "se", "sse" -> MR.strings.wind_direction_southeast
        "s" -> MR.strings.wind_direction_south
        "ssw", "sw", "wsw" -> MR.strings.wind_direction_southwest
        "w" -> MR.strings.wind_direction_west
        "wnw", "nw", "nnw" -> MR.strings.wind_direction_northwest
        else -> throw IllegalArgumentException("Invalid wind direction")
    }

fun parseTime(localTimeEpoch: Long, timeZoneId: String) =
    Instant.fromEpochSeconds(localTimeEpoch).toLocalDateTime(TimeZone.of(timeZoneId))

fun formatTime(currentTime: LocalDateTime): String {
    return "${currentTime.dayOfMonth} ${
        currentTime.month.name.lowercase().capitalize(
            Locale.current
        )
    } ${currentTime.time.hour}:${currentTime.time.minute}"
}

fun isSameDay(currentTimeEpoch: Long, localTimeEpoch: Long, timeZoneId: String): Boolean {
    val currentDateTime = parseTime(currentTimeEpoch, timeZoneId)
    val localTime = parseTime(localTimeEpoch, timeZoneId)

    return currentDateTime.dayOfMonth == localTime.dayOfMonth &&
            currentDateTime.monthNumber == localTime.monthNumber &&
            currentDateTime.year == localTime.year
}

fun getHour(timeEpoch: Long, timeZoneId: String) = parseTime(
    timeEpoch, timeZoneId
).hour

fun getForecastHours(
    forecastDays: List<ForecastDay>,
    timeZoneId: String,
    currentTimeEpochSec: Long
): List<HourUi> {
    val hours = mutableListOf<Hour>()
    forecastDays.map {
        hours.addAll(it.hours)
    }

    val filter = hours.filter {hour ->
        val isSameDay = isSameDay(
            currentTimeEpoch = currentTimeEpochSec,
            localTimeEpoch = hour.timeEpoch,
            timeZoneId = timeZoneId,
        )

        isSameDay && getHour(hour.timeEpoch, timeZoneId) >= getHour(
            currentTimeEpochSec,
            timeZoneId
        )
    }

    return filter.map { hour ->
        if (filter.first().time == hour.time) {
            val a = hour.toUi()
                .copy(hour = "Now")
            a
        } else {
            hour.toUi()
        }
    }
}
