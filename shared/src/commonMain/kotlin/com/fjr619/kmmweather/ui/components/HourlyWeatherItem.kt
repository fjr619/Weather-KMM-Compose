package com.fjr619.kmmweather.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fjr619.kmmweather.ui.model.HourUi
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.fjr619.kmmweather.MR
import dev.icerock.moko.resources.compose.stringResource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource


@Composable
fun HourlyWeatherItem(
    modifier: Modifier = Modifier,
    hourlyForecasts: List<HourUi>
) {
    LazyRow(
        modifier = modifier.fillMaxWidth().padding(top = 48.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(hourlyForecasts) { hour ->
            HourlyForecast(hourlyForecast = hour)
        }
    }
}

@Composable
fun HourlyForecast(
    modifier: Modifier = Modifier,
    hourlyForecast: HourUi,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.Top,
        ) {
            Text(
                text = hourlyForecast.temperature,
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = stringResource(MR.strings.celsius),
                style = MaterialTheme.typography.labelMedium
            )
        }

        KamelImage(
            resource = asyncPainterResource(data = hourlyForecast.iconUrl),
            contentDescription = null,
            modifier = Modifier.size(40.dp),
        )

        Text(
            text = hourlyForecast.hour,
            style = MaterialTheme.typography.labelMedium
        )
    }
}