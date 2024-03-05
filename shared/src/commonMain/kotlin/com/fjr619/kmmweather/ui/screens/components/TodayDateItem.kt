package com.fjr619.kmmweather.ui.screens.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TodayDateItem(
    currentTime: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = currentTime,
    )
}