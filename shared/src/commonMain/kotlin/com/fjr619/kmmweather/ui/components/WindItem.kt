package com.fjr619.kmmweather.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fjr619.kmmweather.MR
import com.fjr619.kmmweather.ui.model.HourUi
import com.fjr619.kmmweather.ui.screens.today.TodayWeatherUiState
import com.fjr619.kmmweather.ui.screens.today.getWindColor
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun WindItem(
    modifier: Modifier = Modifier,
    hourlyForecasts: List<HourUi>,
    headerSection: @Composable () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        SectionTitle(title = stringResource(MR.strings.wind))
        headerSection()
        HourlyWindItem(hourlyForecasts = hourlyForecasts)
    }
}

@Composable
fun HourlyWindItem    (
    modifier: Modifier = Modifier,
    hourlyForecasts: List<HourUi>
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(hourlyForecasts) { hour ->
            HourlyWindForecast(
                hourlyForecast = hour,
                color = getWindColor(hour.windSpeed),
            )
        }
    }
}

@Composable
fun HourlyWindForecast(
    hourlyForecast: HourUi,
    color: Color,
    modifier: Modifier = Modifier,
) {

    //autostart
//    val barHeightAnimation = remember {
//        Animatable(0f)
//    }
//    LaunchedEffect(Unit) {
//        barHeightAnimation.animateTo(
//            hourlyForecast.windSpeed.coerceAtMost(32).toFloat(),
//            tween(1000)
//        )
//    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = Icons.Filled.Send,
            contentDescription = null,
            modifier = Modifier
                .size(16.dp)
                .rotate(-90f + hourlyForecast.windDirectionDegrees)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = hourlyForecast.windSpeed.toString(),
            style = MaterialTheme.typography.labelMedium,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .height(32.dp)
                .width(16.dp),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Box(
                modifier = Modifier
                    .height(hourlyForecast.windSpeed.coerceAtMost(32).dp)
                    .width(16.dp)
                    .background(color = color)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = hourlyForecast.hour,
            style = MaterialTheme.typography.labelMedium,
        )
    }
}

@Composable
fun WindToday(
    modifier: Modifier = Modifier,
    todayWeatherUiState: TodayWeatherUiState
) {

    Row(
        modifier = modifier.height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        Text(
            modifier = Modifier.alignBy(LastBaseline),
            text = todayWeatherUiState.windSpeed.toString(),
            style = MaterialTheme.typography.headlineLarge,
            color = todayWeatherUiState.windSpeedColor,
            fontSize = 56.sp,
        )

        Column(
            modifier = Modifier.alignBy(LastBaseline),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Bottom),
        ) {
            Icon(
                imageVector = Icons.Filled.Send,
                contentDescription = null,
                modifier = Modifier
                    .size(16.dp)
                    .rotate(-90f + todayWeatherUiState.windDirectionDegrees)
            )
            Text(
                text = stringResource(MR.strings.km_h),
                style = MaterialTheme.typography.labelMedium,
            )

        }

        Column(
            modifier = Modifier.padding(start = 16.dp).alignBy(LastBaseline),
        ) {
            Text(
                text = stringResource(todayWeatherUiState.windSpeedDescription),
            )
            todayWeatherUiState.windDirection?.let {
                Text(
                    text = stringResource(todayWeatherUiState.windDirection),
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        }
    }

}