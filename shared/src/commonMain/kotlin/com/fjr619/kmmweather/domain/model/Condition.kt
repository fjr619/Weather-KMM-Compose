package com.fjr619.kmmweather.domain.model

data class Condition(
    val condition: String,
    val iconUrl: String,
    val code: Int,
)