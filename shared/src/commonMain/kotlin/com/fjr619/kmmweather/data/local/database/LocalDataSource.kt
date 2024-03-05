package com.fjr619.kmmweather.data.local.database

import com.fjr619.kmmweather.data.model.dao.LocationDao
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getLocations(): Flow<List<LocationDao>>
    suspend fun insertLocation(location: LocationDao)
    suspend fun deleteLocation(id: Long)
    suspend fun getLocationById(location: String): List<LocationDao>
}