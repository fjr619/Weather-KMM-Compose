package com.fjr619.kmmweather.di

import com.fjr619.kmmweather.WeatherDatabase
import com.fjr619.kmmweather.data.local.database.LocalDataSource
import com.fjr619.kmmweather.data.local.database.LocalDataSourceImpl
import com.fjr619.kmmweather.data.local.datastore.PreferencesDataSource
import com.fjr619.kmmweather.data.local.datastore.PreferencesDataSourceImpl
import com.fjr619.kmmweather.data.remote.RemoteDataSource
import com.fjr619.kmmweather.data.remote.RemoteDataSourceImpl
import com.fjr619.kmmweather.data.remote.createHttpClient
import org.koin.dsl.module

val dataModule = module {
    single<PreferencesDataSource> { PreferencesDataSourceImpl(get()) }
    single<LocalDataSource> { LocalDataSourceImpl(
        database = WeatherDatabase(driver = get())
    ) }
    single { createHttpClient(get()) }
    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }
}