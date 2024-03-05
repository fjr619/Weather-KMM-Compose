package com.fjr619.kmmweather.data.model.dto

import com.fjr619.kmmweather.domain.model.SearchResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResultDto(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("region") val region: String,
    @SerialName("country") val country: String,
    @SerialName("lat") val latitude: Double,
    @SerialName("lon") val longitude: Double,
    @SerialName("url") val url: String,
)

internal fun SearchResultDto.toDomain() = SearchResult(
    id = id,
    name = name,
    region = region,
    country = country,
    latitude = latitude,
    longitude = longitude,
)