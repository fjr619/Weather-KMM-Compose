package com.fjr619.kmmweather.di

import co.touchlab.kermit.Logger
import co.touchlab.kermit.StaticConfig
import co.touchlab.kermit.loggerConfigInit
import co.touchlab.kermit.platformLogWriter
import com.fjr619.kmmweather.data.remote.RemoteDataSourceImpl
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(
        platformModule(),
        dataModule,
        domainModule,
        loggerModule,
        viewModelModule
    )
}

//called by iOS etc
fun initKoin() = initKoin { }

inline fun <reified T> Scope.getWith(vararg params: Any?): T {
    return get(parameters = { parametersOf(*params) })
}

inline fun <reified T> KoinComponent.getWith(vararg params: Any?): T {
    return get(parameters = { parametersOf(*params) })
}

// Simple function to clean up the syntax a bit
fun KoinComponent.injectLogger(tag: String): Lazy<Logger> = inject { parametersOf(tag) }
