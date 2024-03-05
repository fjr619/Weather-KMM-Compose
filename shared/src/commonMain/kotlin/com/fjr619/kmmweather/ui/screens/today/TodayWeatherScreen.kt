package com.fjr619.kmmweather.ui.screens.today

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fjr619.kmmweather.di.ViewModelFac
import com.fjr619.kmmweather.ui.screens.components.TodayDateItem
import com.fjr619.kmmweather.ui.screens.components.TodayWeatherItem
import com.fjr619.kmmweather.ui.screens.main.MainEvent
import com.fjr619.kmmweather.ui.screens.main.MainUiState

@Composable
fun TodayWeatherScreen(
    modifier: Modifier = Modifier,
    mainUiState: MainUiState,
    updateMainUiState: (MainEvent) -> Unit
) {
    val viewModel = ViewModelFac.getTodayWeatherViewModel()

    LaunchedEffect(mainUiState.forecast) {
        mainUiState.forecast?.let {
            viewModel.onEvent(TodayWeatherEvent.UpdateForecast(it))
        }
        updateMainUiState(MainEvent.Loading(false))
    }

    val state by viewModel.state.collectAsState()

    when {
        mainUiState.isLoading -> {
            Text(text = "LOADING")
        }

        mainUiState.isError -> {
            Text(text = "ERROR")
        }

        else -> {
            TodayWeatherContent(state)
        }
    }
}

@Composable
fun TodayWeatherContent(
    state: TodayWeatherUiState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            TodayDateItem(state.currentTime)
        }

        item {
            TodayWeatherItem(state)
        }
    }
}