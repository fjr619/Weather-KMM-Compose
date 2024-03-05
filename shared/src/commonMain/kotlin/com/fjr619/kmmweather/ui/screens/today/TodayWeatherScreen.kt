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
import com.fjr619.kmmweather.MR
import com.fjr619.kmmweather.di.ViewModelFac
import com.fjr619.kmmweather.ui.components.HourlyWeatherItem
import com.fjr619.kmmweather.ui.components.PrecipitationChanceItem
import com.fjr619.kmmweather.ui.components.DateItem
import com.fjr619.kmmweather.ui.components.DetailsItem
import com.fjr619.kmmweather.ui.components.DividerItem
import com.fjr619.kmmweather.ui.components.WeatherItem
import com.fjr619.kmmweather.ui.screens.main.MainEvent
import com.fjr619.kmmweather.ui.screens.main.MainUiState
import dev.icerock.moko.resources.compose.stringResource

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
            DateItem(state.currentTime)
        }

        item {
            WeatherItem(state)
        }

        item {
            HourlyWeatherItem(hourlyForecasts = state.hourlyForecasts)
        }

        item {
            PrecipitationChanceItem(
                rainChance = stringResource(
                    MR.strings.today_rain_chance,
                    state.rainChance,
                )
            )
        }

        item {
            DividerItem()
        }

        item {
            DetailsItem(state)
        }

        item {
            DividerItem()
        }
    }
}