package com.example.kcalcul_compose.network.dtos.foodsBeverages

import com.squareup.moshi.Json

class CreateRecipeResponseDto(
    @Json(name = "message")
    val message: String,
    @Json(name = "statusCode")
    val statusCode: Int
)