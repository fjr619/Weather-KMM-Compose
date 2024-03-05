package com.fjr619.kmmweather.domain.repository

import com.fjr619.kmmweather.domain.model.AirQualityEnum
import com.fjr619.kmmweather.domain.model.Forecast
import com.fjr619.kmmweather.domain.model.Response
import com.fjr619.kmmweather.domain.model.WeatherAlertsEnum
import kotlinx.coroutines.flow.Flow

interface ForecastRepo {
    suspend operator fun invoke(
        query: String,
        airQuality: AirQualityEnum,
        weatherAlerts: WeatherAlertsEnum,
        days: Int,
    ): Flow<Response<Forecast>>
}