package com.fjr619.kmmweather.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DividerItem(
    modifier: Modifier = Modifier,
) {
    Divider(modifier = modifier.fillMaxWidth())
}