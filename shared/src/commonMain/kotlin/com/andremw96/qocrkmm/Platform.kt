package com.andremw96.qocrkmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform