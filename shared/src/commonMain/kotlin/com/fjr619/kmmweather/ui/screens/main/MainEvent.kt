package com.fjr619.kmmweather.ui.screens.main

sealed interface MainEvent {
    data class UpdateQuery(val query: String) : MainEvent
    data object LoadForecast : MainEvent
    data class Loading(val isLoading: Boolean = true) : MainEvent
    data object Error : MainEvent
    data class ShowSaveLocationSnackbar(val location: String) : MainEvent
    data class SaveLocation(val location: String): MainEvent
    data object ShowEmptyMessage : MainEvent
    data object RequestLocationPermission : MainEvent
    data class LoadForecastWithLocation(val location: String): MainEvent
}