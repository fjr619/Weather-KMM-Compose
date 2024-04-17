package com.fjr619.kmmweather.di

import com.fjr619.kmmweather.ui.screens.main.MainViewModel
import com.fjr619.kmmweather.ui.screens.today.TodayWeatherViewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModelDefinition {
        MainViewModel(
            permissionsController = get(),
            dataStore = get(),
            locationService = get(),
            forecastRepo = get(),
            logger = getWith(MainViewModel::class.simpleName)
        )
    }

    viewModelDefinition {
        TodayWeatherViewModel(
            logger = getWith(TodayWeatherViewModel::class.simpleName)
        )
    }
}