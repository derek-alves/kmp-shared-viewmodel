package com.example.study

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform