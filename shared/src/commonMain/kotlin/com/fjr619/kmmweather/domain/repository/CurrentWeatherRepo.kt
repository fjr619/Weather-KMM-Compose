package com.fjr619.kmmweather.domain.repository

import com.fjr619.kmmweather.domain.model.AirQualityEnum
import com.fjr619.kmmweather.domain.model.CurrentWeather
import com.fjr619.kmmweather.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface CurrentWeatherRepo {
    fun getCurrentWeather(
        query: String,
        airQualityEnum: AirQualityEnum
    ): Flow<Response<CurrentWeather>>
}