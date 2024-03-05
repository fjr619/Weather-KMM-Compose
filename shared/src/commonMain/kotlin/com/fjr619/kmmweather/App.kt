package com.fjr619.kmmweather

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.fjr619.kmmweather.ui.screens.main.MainScreen
import com.fjr619.kmmweather.ui.theme.AppTheme

@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean = false,
) {
    AppTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor,
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
//            Box(
//               modifier = Modifier.fillMaxSize(),
//                contentAlignment = Alignment.Center
//            ) {
//                GreetingView(Greeting().greet())
//            }
            MainScreen()
        }

    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}