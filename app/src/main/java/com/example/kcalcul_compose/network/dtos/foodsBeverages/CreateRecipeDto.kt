package com.example.kcalcul_compose.network.dtos.foodsBeverages


import com.squareup.moshi.Json

data class CreateRecipeDto(
    @Json(name = "name")
    val name: String,
    @Json(name = "state")
    val state: String,
    @Json(name = "dish")
    val dish: String,
    @Json(name = "foodBeverages")
    val foodBeverages: List<FoodBeverageDto>,
    @Json(name = "quantity")
    val quantity: List<Int>,
    @Json(name = "unit")
    val unit: List<UnitDto>,
    @Json(name = "idU")
    val id: Long
)