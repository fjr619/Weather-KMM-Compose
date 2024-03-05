package com.fjr619.kmmweather.ui.screens.today

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.fjr619.kmmweather.domain.model.Forecast
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
)

internal fun MutableStateFlow<TodayWeatherUiState>.setResponse(result: Forecast) {
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
        )
    }
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
