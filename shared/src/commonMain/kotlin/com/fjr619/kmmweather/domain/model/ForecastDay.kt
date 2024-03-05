package com.fjr619.kmmweather.domain.model

data class ForecastDay(
    val date: String,
    val dateEpoch: Long,
    val day: Day,
    val astronomy: Astronomy,
    val hours: List<Hour>,
)