package com.fjr619.kmmweather.data.local.datastore

import kotlinx.coroutines.flow.Flow

interface PreferencesDataSource {
    val location: Flow<String>

    suspend fun saveLocation(location: String)
}