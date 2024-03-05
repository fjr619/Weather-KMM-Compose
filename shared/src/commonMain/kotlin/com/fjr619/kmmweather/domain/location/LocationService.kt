package com.fjr619.kmmweather.domain.location

interface LocationService {
    suspend fun getCurrentLocation(): DeviceLocation
}