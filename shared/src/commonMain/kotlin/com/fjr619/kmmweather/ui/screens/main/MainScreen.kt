package com.fjr619.kmmweather.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.fjr619.kmmweather.MR
import com.fjr619.kmmweather.di.ViewModelFac
import com.fjr619.kmmweather.ui.screens.today.TodayWeatherScreen
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val permissionFactory = rememberPermissionsControllerFactory()
    val viewModel = ViewModelFac.getMainViewModel(permissionFactory)

    val state by viewModel.state.collectAsState()
    BindEffect(viewModel.permissionsController)

    Scaffold(
        modifier = modifier,
        snackbarHost = {},
        topBar = {}
    ) { padding ->
        Surface(
            modifier = Modifier.fillMaxSize()
                .padding(padding),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                if (state.showEmptyMessage) {
                    EmptyLocationMessage()
                } else {
                    TodayWeatherScreen(mainUiState = state) {
                        viewModel.onEvent(it)
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyLocationMessage(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(MR.strings.empty_query_message))
    }
}