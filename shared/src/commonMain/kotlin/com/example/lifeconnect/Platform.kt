package com.example.lifeconnect

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform