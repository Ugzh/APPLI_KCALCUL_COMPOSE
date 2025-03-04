package com.example.kcalcul.network.dtos.users

import com.squareup.moshi.Json

data class UpdateUserResponseDto(
    @Json(name = "message")
    val message: String,
    @Json(name = "statusCode")
    val statusCode: Int
)
