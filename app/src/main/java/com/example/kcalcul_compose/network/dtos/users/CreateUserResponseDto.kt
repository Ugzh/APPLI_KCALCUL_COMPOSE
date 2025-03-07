package com.example.kcalcul_compose.network.dtos.users


import com.squareup.moshi.Json

data class CreateUserResponseDto(
    @Json(name = "statusCode")
    val statusCode: Int,
    @Json(name = "message")
    val message: String
)