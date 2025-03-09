package com.example.kcalcul_compose.network.dtos.meal


import com.squareup.moshi.Json

data class CreateMealResponseDto(
    @Json(name = "status")
    val status: Int,
    @Json(name = "date")
    val date: String,
    @Json(name = "notification")
    val notification: Boolean,
    @Json(name = "mealType")
    val mealType: String
)