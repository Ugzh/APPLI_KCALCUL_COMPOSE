package com.example.kcalcul.network.dtos.foodsBeverages

import com.squareup.moshi.Json

class CreateRecipeResponseDto(
    @Json(name = "message")
    val message: String,
    @Json(name = "statusCode")
    val statusCode: Int
)