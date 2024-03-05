package com.fjr619.kmmweather.ui.screens.main

import com.fjr619.kmmweather.domain.model.Forecast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class MainUiState(
    val query: String = "",
    val forecast: Forecast? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
//    val locationToSave: String = "",
    val showEmptyMessage: Boolean = false,
)

internal fun MutableStateFlow<MainUiState>.updateQuery(query: String) {
    update { it.copy(query = query) }
}

internal fun MutableStateFlow<MainUiState>.isLoading(isLoading: Boolean = true) {
    update { it.copy(isLoading = isLoading, showEmptyMessage = false, isError = false) }
}

internal fun MutableStateFlow<MainUiState>.isError() {
    update { it.copy(isError = true, isLoading = false, showEmptyMessage = false) }
}

internal fun MutableStateFlow<MainUiState>.showEmptyMessage() {
    update { it.copy(isError = true, isLoading = false, showEmptyMessage = true) }
}

internal fun MutableStateFlow<MainUiState>.updateForecast(forecast: Forecast) {
    update {
        it.copy(
//            isLoading = false,
            isError = false,
            forecast = forecast,
            showEmptyMessage = false,
            query = with (forecast.location) {
                "$name, $region, $country"
            }
        )
    }
}