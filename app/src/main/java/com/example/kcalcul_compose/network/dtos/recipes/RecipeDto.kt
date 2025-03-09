package com.example.kcalcul_compose.network.dtos.recipes


import com.squareup.moshi.Json

data class RecipeDto(
    @Json(name = "id")
    val id: Long,
    @Json(name = "name")
    val name: String,
    @Json(name = "dish")
    val dish: String,
    @Json(name = "state")
    val state: String,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "updated_at")
    val updatedAt: String?
)