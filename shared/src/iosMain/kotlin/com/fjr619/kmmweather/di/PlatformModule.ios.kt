package com.fjr619.kmmweather.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.fjr619.kmmweather.WeatherDatabase
import com.fjr619.kmmweather.data.local.DataStoreProviderImpl
import com.fjr619.kmmweather.data.local.datastore.DataStoreProvider
import com.fjr619.kmmweather.domain.location.IosLocationService
import com.fjr619.kmmweather.domain.location.LocationService
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single<DataStoreProvider> { DataStoreProviderImpl() }
    single<SqlDriver> {
        NativeSqliteDriver(WeatherDatabase.Schema, "WeatherDatabase.db")
    }
    single { Darwin.create() }
    factory<LocationService> { IosLocationService() }
}

