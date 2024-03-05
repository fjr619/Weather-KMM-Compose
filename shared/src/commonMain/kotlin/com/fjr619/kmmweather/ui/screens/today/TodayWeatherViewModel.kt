package com.fjr619.kmmweather.ui.screens.today

import co.touchlab.kermit.Logger
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TodayWeatherViewModel(
    private val logger: Logger
): ViewModel() {

    private val _state = MutableStateFlow(TodayWeatherUiState())
    val state = _state.asStateFlow()

    fun onEvent(event: TodayWeatherEvent) {
        when(event) {
            is TodayWeatherEvent.UpdateForecast -> {
                logger.i("update today forecast")
                event.forecast?.let { forecast ->
                    _state.setResponse(forecast)
                }
            }
        }
    }
}