package com.fjr619.kmmweather.di

import androidx.compose.runtime.Composable
import com.fjr619.kmmweather.ui.screens.main.MainViewModel
import com.fjr619.kmmweather.ui.screens.today.TodayWeatherViewModel
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import dev.icerock.moko.permissions.compose.PermissionsControllerFactory
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

object ViewModelFac : KoinComponent {

    @Composable
    fun getMainViewModel(permissionFactory: PermissionsControllerFactory) =
        getViewModel(
            key = "main-vm",
            factory = viewModelFactory {
                MainViewModel(
                    permissionsController = permissionFactory.createPermissionsController(),
                    dataStore = get(),
                    locationService = get(),
                    forecastRepo = get(),
                    logger = getWith("MainViewModel"),
                )
            }
        )

    @Composable
    fun getTodayWeatherViewModel() =
        getViewModel(
            key = "today-vm",
            factory = viewModelFactory {
                TodayWeatherViewModel(
                    logger = getWith("TodayWeatherViewModel")
                )
            }
        )
}