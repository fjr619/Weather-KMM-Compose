package com.fjr619.kmmweather.domain.model

enum class AirQualityEnum(val value: String) {
    YES("yes"),
    NO("no"),
}

enum class WeatherAlertsEnum(val value: String) {
    YES("yes"),
    NO("no"),
}

enum class TimeOfDayEnum {
    Day, Night,
}