package com.example.kcalcul.network.dtos.users


import com.squareup.moshi.Json

data class LogUserResponseDto(
    @Json(name = "user")
    val user: UserDto,
    @Json(name="token")
    val token: String,
    )