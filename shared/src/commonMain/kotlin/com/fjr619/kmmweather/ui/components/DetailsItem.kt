package com.fjr619.kmmweather.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fjr619.kmmweather.MR
import com.fjr619.kmmweather.ui.screens.today.TodayWeatherUiState
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun DetailsItem(
    todayWeatherUiState: TodayWeatherUiState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        SectionTitle(title = stringResource(MR.strings.current_details),)
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            DetailsHeaders()
            DetailsValues(todayWeatherUiState)
        }
    }
}

@Composable
private fun RowScope.DetailsHeaders() {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = stringResource(MR.strings.humidity),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
        )
        Text(
            text = stringResource(MR.strings.dew_point),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
        )
        Text(
            text = stringResource(MR.strings.pressure),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
        )
        Text(
            text = stringResource(MR.strings.uv_index),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
        )
        Text(
            text = stringResource(MR.strings.visibility),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
        )
    }
}

@Composable
private fun RowScope.DetailsValues(todayWeatherUiState: TodayWeatherUiState) {
    Column(
        modifier = Modifier.weight(1f),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = stringResource(MR.strings.humidity_percentage, todayWeatherUiState.humidity),
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = stringResource(MR.strings.temperature_celsius, todayWeatherUiState.dewPoint),
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = stringResource(MR.strings.pressure_mbar, todayWeatherUiState.pressure),
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = todayWeatherUiState.uvIndex,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = stringResource(MR.strings.visibility_km, todayWeatherUiState.visibility),
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
        )
    }
}