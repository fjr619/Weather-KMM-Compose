package com.fjr619.kmmweather.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fjr619.kmmweather.MR
import com.fjr619.kmmweather.ui.model.HourUi
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun HourlyRainItem(
    hourlyForecasts: List<HourUi>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        SectionTitle(title = stringResource(MR.strings.precipitation),)

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = stringResource(MR.strings.rain_chance),
                        style = MaterialTheme.typography.labelMedium,
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(
                        text = stringResource(MR.strings.rain_volume),
                        style = MaterialTheme.typography.labelMedium,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
            }

            items(hourlyForecasts) { hour ->
                HourlyRainForecast(hourlyForecast = hour)
            }
        }
    }
}
@Composable
fun HourlyRainForecast(
    hourlyForecast: HourUi,
    modifier: Modifier = Modifier,
) {

    //autostart
//    val barHeightAnimation = remember {
//        Animatable(0f)
//    }
//    LaunchedEffect(Unit) {
//        barHeightAnimation.animateTo(
//            hourlyForecast.precipitationMmPercentage.toFloat(),
//            tween(1000)
//        )
//    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(MR.strings.chance_rain_percentage, hourlyForecast.chanceOfRain),
            style = MaterialTheme.typography.labelMedium,
        )

        Box(
            modifier = Modifier
                .height(32.dp)
                .width(16.dp),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Box(
                modifier = Modifier
                    .height((32 * hourlyForecast.precipitationMmPercentage).dp)
                    .width(16.dp)
                    .background(color = MaterialTheme.colorScheme.primary)
            )
        }
        Text(
            text = hourlyForecast.precipitationMm,
            style = MaterialTheme.typography.labelMedium,
        )
        Text(
            text = hourlyForecast.hour,
            style = MaterialTheme.typography.labelMedium,
        )
    }
}

@Composable
fun TotalDailyRainVolume(
    totalPrecipitation: String,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Text(
            text = stringResource(MR.strings.rain_total_daily_volume),
            style = MaterialTheme.typography.labelMedium,
        )
        Text(
            text = totalPrecipitation,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
        )
    }
}