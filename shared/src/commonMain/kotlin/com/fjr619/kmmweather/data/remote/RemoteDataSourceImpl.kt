package com.fjr619.kmmweather.data.remote

import co.touchlab.kermit.Logger
import com.fjr619.kmmweather.data.model.dto.CurrentWeatherResponseDto
import com.fjr619.kmmweather.data.model.dto.ForecastResponseDto
import com.fjr619.kmmweather.data.model.dto.SearchResultDto
import com.fjr619.kmmweather.data.remote.request.CurrentWeatherRequest
import com.fjr619.kmmweather.data.remote.request.ForecastRequest
import com.fjr619.kmmweather.data.remote.request.SearchRequest
import com.fjr619.kmmweather.domain.model.AirQualityEnum
import com.fjr619.kmmweather.domain.model.WeatherAlertsEnum
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.resources.get

class RemoteDataSourceImpl(
    private val httpClient: HttpClient,
    private val logger: Logger
) : RemoteDataSource {
    override suspend fun fetchCurrentWeather(
        query: String,
        airQuality: AirQualityEnum
    ): CurrentWeatherResponseDto {
        return httpClient.get(
            CurrentWeatherRequest(
                query = query,
                airQuality = airQuality.value
            )
        ).body()
    }

    override suspend fun fetchForecast(
        query: String,
        airQuality: AirQualityEnum,
        weatherAlerts: WeatherAlertsEnum,
        days: Int
    ): ForecastResponseDto {
        return httpClient.get(
            ForecastRequest(
                query = query,
                airQuality = airQuality.value,
                alerts = weatherAlerts.value,
                days = days.coerceAtMost(3)
            )
        ).body()
    }

    override suspend fun search(query: String): List<SearchResultDto> {
        return httpClient.get(
            SearchRequest(
                query = query
            )
        ).body()
    }
}