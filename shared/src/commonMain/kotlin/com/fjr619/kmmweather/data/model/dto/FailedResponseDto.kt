package com.fjr619.kmmweather.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FailedResponseDto (
    @SerialName("error") val failedDto: FailedDto
)

@Serializable
data class FailedDto(
    @SerialName("code") val statusCode: Int,
    @SerialName("message")val statusMessage: String?
)