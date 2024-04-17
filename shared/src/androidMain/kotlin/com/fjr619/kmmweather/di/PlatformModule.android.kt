package com.fjr619.kmmweather.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.fjr619.kmmweather.WeatherDatabase
import com.fjr619.kmmweather.data.local.DataStoreProviderImpl
import com.fjr619.kmmweather.data.local.datastore.DataStoreProvider
import com.fjr619.kmmweather.domain.location.AndroidLocationService
import com.fjr619.kmmweather.domain.location.LocationService
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.client.engine.android.Android
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.definition.Definition
import org.koin.core.definition.KoinDefinition

actual fun platformModule(): Module = module {
    single<DataStoreProvider> { DataStoreProviderImpl(androidContext()) }
    single<SqlDriver> { AndroidSqliteDriver(
        context = androidContext(),
        name = "WeatherDatabase.db",
        schema = WeatherDatabase.Schema
    ) }
    single { Android.create() }
    factory <LocationService> { AndroidLocationService(context = androidContext()) }
}
actual inline fun <reified T : ViewModel> Module.viewModelDefinition(
    qualifier: Qualifier?,
    noinline definition: Definition<T>,
): KoinDefinition<T> = viewModel(qualifier = qualifier, definition = definition)