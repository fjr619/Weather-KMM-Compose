package com.fjr619.kmmweather.di

import com.fjr619.kmmweather.ui.screens.main.MainViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import dev.icerock.moko.permissions.compose.PermissionsControllerFactory
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

object ViewModelFac : KoinComponent {
    fun getMainVmFactory(permissionFactory: PermissionsControllerFactory) =
        viewModelFactory {
            MainViewModel(
                permissionsController = permissionFactory.createPermissionsController(),
                dataStore = get(),
                locationService = get(),
                forecastRepo = get(),
                logger = getWith("MainViewModel"),
            )
        }
}