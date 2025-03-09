package com.example.kcalcul_compose.network.dtos.foodsBeverages


import com.squareup.moshi.Json

data class FoodBeverageResponseDto(
    @Json(name = "id")
    val id: Long,
    @Json(name = "name")
    val name: String,
    @Json(name = "kcal")
    val kcal: Int,
    @Json(name = "state")
    val state: String,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "updated_at")
    val updatedAt: String?
)