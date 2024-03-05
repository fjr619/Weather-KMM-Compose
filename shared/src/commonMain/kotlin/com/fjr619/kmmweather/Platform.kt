package com.fjr619.kmmweather

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform