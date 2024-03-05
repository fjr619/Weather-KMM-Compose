package com.fjr619.kmmweather.di

import co.touchlab.kermit.Logger
import co.touchlab.kermit.StaticConfig
import co.touchlab.kermit.platformLogWriter
import org.koin.dsl.module

val loggerModule = module {
    // platformLogWriter() is a relatively simple config option, useful for local debugging. For production
    // uses you *may* want to have a more robust configuration from the native platform. In KaMP Kit,
    // that would likely go into platformModule expect/actual.
    // See https://github.com/touchlab/Kermit
    val baseLogger =
        Logger(config = StaticConfig(logWriterList = listOf(platformLogWriter())), "WeatherApp")
    factory { (tag: String?) -> if (tag != null) baseLogger.withTag(tag) else baseLogger }
}