package com.fjr619.kmmweather.ui.screens.today

import com.fjr619.kmmweather.domain.model.Forecast

sealed class TodayWeatherEvent {
    data class UpdateForecast(val forecast: Forecast?) : TodayWeatherEvent()
}