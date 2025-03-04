package com.example.kcalcul.network.dtos.foodsBeverages


import com.squareup.moshi.Json

data class FoodBeverageDto(
    @Json(name = "name")
    val name: String,
    @Json(name = "kcal")
    val kcal: Int,
    @Json(name = "state")
    val state: String
)