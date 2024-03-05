package com.fjr619.kmmweather.di

import com.fjr619.kmmweather.data.repository.CurrentWeatherRepoImpl
import com.fjr619.kmmweather.data.repository.ForecastRepoImpl
import com.fjr619.kmmweather.domain.repository.CurrentWeatherRepo
import com.fjr619.kmmweather.domain.repository.ForecastRepo
import org.koin.dsl.module

val domainModule = module {
    factory<CurrentWeatherRepo> { CurrentWeatherRepoImpl(get(), getWith("CurrentWeatherRepo")) }
    factory<ForecastRepo> { ForecastRepoImpl(get(), getWith("ForecastRepo")) }
}