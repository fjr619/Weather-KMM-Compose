package com.fjr619.kmmweather.ui.screens.main

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class PermissionsState(
    val isGranted: Boolean? = null,
    val isPermanentlyDenied: Boolean? = null,
    val isDenied: Boolean? = null,
)

internal fun MutableStateFlow<PermissionsState>.granted() = update {
    it.copy(isGranted = true, isPermanentlyDenied = null, isDenied = null)
}

internal fun MutableStateFlow<PermissionsState>.permanentlyDenied() = update {
    it.copy(isGranted = null, isPermanentlyDenied = true, isDenied = null)
}

internal fun MutableStateFlow<PermissionsState>.denied() = update {
    it.copy(isGranted = null, isPermanentlyDenied = null, isDenied = true)
}