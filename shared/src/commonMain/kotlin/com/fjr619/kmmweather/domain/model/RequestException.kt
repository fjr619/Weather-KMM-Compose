package com.fjr619.kmmweather.domain.model

class RequestException(override val message: String?, val statusCode: Int) : Exception(message)
