package com.example.kcalcul_compose.network.dtos.users


import com.squareup.moshi.Json

data class LogUserDto(
    @Json(name = "email")
    val email: String,
    @Json(name = "password")
    val password: String
)