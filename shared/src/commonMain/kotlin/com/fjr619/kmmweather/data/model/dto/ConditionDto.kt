package com.fjr619.kmmweather.data.model.dto

import com.fjr619.kmmweather.domain.model.Condition
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConditionDto(
    @SerialName("text") val condition: String,
    @SerialName("icon") val iconUrl: String,
    @SerialName("code") val code: Int,
)

internal fun ConditionDto.toDomain() = Condition(
    condition = condition,
    iconUrl = iconUrl,
    code = code,
)