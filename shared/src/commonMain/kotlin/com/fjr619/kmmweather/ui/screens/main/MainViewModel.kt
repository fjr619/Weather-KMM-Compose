package com.fjr619.kmmweather.ui.screens.main

import co.touchlab.kermit.Logger
import com.fjr619.kmmweather.data.local.datastore.PreferencesDataSource
import com.fjr619.kmmweather.di.injectLogger
import com.fjr619.kmmweather.domain.location.DeviceLocation
import com.fjr619.kmmweather.domain.location.LocationService
import com.fjr619.kmmweather.domain.model.AirQualityEnum
import com.fjr619.kmmweather.domain.model.Response
import com.fjr619.kmmweather.domain.model.WeatherAlertsEnum
import com.fjr619.kmmweather.domain.repository.ForecastRepo
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.icerock.moko.permissions.DeniedAlwaysException
import dev.icerock.moko.permissions.DeniedException
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionState
import dev.icerock.moko.permissions.PermissionsController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class MainViewModel(
    val permissionsController: PermissionsController,

    // ga boleh akses langsung ke data layer, harus pakai domain layer
    private val dataStore: PreferencesDataSource,

    private val locationService: LocationService,
    private val forecastRepo: ForecastRepo,
    private val logger: Logger
): ViewModel(), KoinComponent {
    private val permissions = MutableStateFlow(PermissionsState())
    private val _state = MutableStateFlow(MainUiState())
    val state: StateFlow<MainUiState> = _state.asStateFlow()

    private val locationPermissionsFlow = dataStore.location
        .combine(permissions) { location, permissions ->
            when {
                location.isEmpty() && (permissions.isDenied == true || permissions.isPermanentlyDenied == true) -> {
                    MainEvent.ShowEmptyMessage

                }
                location.isEmpty() && permissions.isGranted == true -> {
                    MainEvent.LoadForecastWithLocation(location)
                }
                else -> {
                    MainEvent.UpdateQuery(query = location)
                }
            }
        }

    init {
        viewModelScope.launch {
            locationPermissionsFlow.collectLatest { onEvent(it) }
        }

        viewModelScope.launch {
            when(permissionsController.getPermissionState(Permission.LOCATION)) {
                PermissionState.NotDetermined -> onEvent(MainEvent.RequestLocationPermission)
                PermissionState.Granted -> permissions.granted()
                else -> {}
            }
        }
    }

    fun onEvent(event: MainEvent) {
        when(event) {
            is MainEvent.Loading -> _state.isLoading(event.isLoading)
            is MainEvent.Error -> _state.isError()
            is MainEvent.ShowEmptyMessage -> { _state.showEmptyMessage() }
            is MainEvent.RequestLocationPermission -> requestPermission()
            is MainEvent.LoadForecastWithLocation -> loadForecastWithLocation()
            else -> {}
        }
    }

    private fun requestPermission() {
        viewModelScope.launch {
            try {
                permissionsController.providePermission(Permission.LOCATION)
                permissions.granted()
            } catch (e: DeniedAlwaysException) {
                permissions.permanentlyDenied()
            } catch (e: DeniedException) {
                permissions.denied()
            } finally {
                permissionsController.getPermissionState(Permission.LOCATION)
                    .also {
                        if (it == PermissionState.NotDetermined) requestPermission()
                    }
            }
        }
    }

    private fun loadForecastWithLocation() {
        viewModelScope.launch {
            val location: DeviceLocation = locationService.getCurrentLocation()
            loadForecast(location.toString())
        }
    }

    private fun loadForecast(query: String) {
        if (query.isNotEmpty()) {
            viewModelScope.launch {
                onEvent(MainEvent.Loading())
                forecastRepo.invoke(
                    query = query,
                    airQuality = AirQualityEnum.YES,
                    days = 3,
                    weatherAlerts = WeatherAlertsEnum.YES
                ).collectLatest { result ->
                    when(result) {
                        is Response.Error -> {
                            onEvent(MainEvent.Loading(false))
                            onEvent(MainEvent.Error)
                        }
                        is Response.Success -> {
                            _state.updateForecast(result.data)
                        }
                    }
                    onEvent(MainEvent.Loading(false))
                }
            }
        }
    }
}