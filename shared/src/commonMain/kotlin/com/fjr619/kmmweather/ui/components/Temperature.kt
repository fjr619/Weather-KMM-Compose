package com.fjr619.kmmweather.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fjr619.kmmweather.MR
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun Temperature(
    currentTemperature: String,
    feelsLike: String,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = currentTemperature,
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontSize = 72.sp
                )
            )
            Text(
                text = stringResource(MR.strings.celsius),
                style = MaterialTheme.typography.headlineMedium
            )
        }

        Text(
            text = stringResource(MR.strings.feels_like_temp, feelsLike)
        )
    }

}