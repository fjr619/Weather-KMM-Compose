package com.fjr619.kmmweather.data.remote

import com.fjr619.kmmweather.data.model.dto.FailedResponseDto
import com.fjr619.kmmweather.domain.model.RequestException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.resources.Resources
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun createHttpClient(httpClientEngine: HttpClientEngine) = HttpClient(httpClientEngine) {
    expectSuccess = true
    install(Resources)
    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }

    install(Logging) {
        level = LogLevel.ALL
        logger = object : Logger {
            override fun log(message: String) {
                println(message)
            }
        }
    }

    HttpResponseValidator {
        handleResponseExceptionWithRequest { exception, request ->
//            val type = when (exception) {
//                is ClientRequestException -> RemoteExceptionType.CLIENT_ERROR
//                is ServerResponseException -> RemoteExceptionType.SERVER_ERROR
//                is JsonConvertException -> RemoteExceptionType.PARSING_ERROR
//                else -> RemoteExceptionType.UNKNOWN
//            }
//            throw RemoteException(type)

            when(exception) {
                is ResponseException -> {
                    val dto = exception.response.body<FailedResponseDto>()
                    throw RequestException(
                        statusCode = dto.failedDto.statusCode,
                        message = dto.failedDto.statusMessage
                    )
                }
            }
        }
    }

    defaultRequest {
        url {
            host = "api.weatherapi.com/v1"
            protocol = URLProtocol.HTTPS
            parameters.append("key", "07e8be4b8bff49d999073159242602")
            contentType(ContentType.Application.Json)
        }
    }
}