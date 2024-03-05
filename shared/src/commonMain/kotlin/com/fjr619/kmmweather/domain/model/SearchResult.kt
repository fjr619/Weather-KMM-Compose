package com.fjr619.kmmweather.domain.model

data class SearchResult(
    val id: Long,
    val name: String,
    val region: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
)