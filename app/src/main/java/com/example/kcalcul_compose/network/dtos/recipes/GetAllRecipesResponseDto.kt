package com.example.kcalcul_compose.network.dtos.recipes


import com.squareup.moshi.Json

data class GetAllRecipesResponseDto(
    @Json(name = "status")
    val status: Int,
    @Json(name = "recipes")
    val recipes: List<RecipeDto>
)