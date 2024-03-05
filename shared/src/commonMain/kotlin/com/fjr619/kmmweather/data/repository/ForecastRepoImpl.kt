package com.fjr619.kmmweather.data.repository

import co.touchlab.kermit.Logger
import com.fjr619.kmmweather.data.model.dto.toDomain
import com.fjr619.kmmweather.data.remote.RemoteDataSource
import com.fjr619.kmmweather.domain.model.AirQualityEnum
import com.fjr619.kmmweather.domain.model.Forecast
import com.fjr619.kmmweather.domain.model.RequestException
import com.fjr619.kmmweather.domain.model.Response
import com.fjr619.kmmweather.domain.model.WeatherAlertsEnum
import com.fjr619.kmmweather.domain.repository.ForecastRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ForecastRepoImpl(
    private val remoteDataSource: RemoteDataSource,
    private val logger: Logger
): ForecastRepo {
    override suspend fun invoke(
        query: String,
        airQuality: AirQualityEnum,
        weatherAlerts: WeatherAlertsEnum,
        days: Int
    ): Flow<Response<Forecast>> = flow {
        try {
            val response = remoteDataSource.fetchForecast(
                query = query,
                airQuality = airQuality,
                weatherAlerts = weatherAlerts,
                days = days
            )
            emit(Response.Success(response.toDomain()) as Response<Forecast>)
        } catch (e: RequestException) {
            emit(Response.Error(e.message))
        } catch (e: Exception) {
            emit(Response.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)
}