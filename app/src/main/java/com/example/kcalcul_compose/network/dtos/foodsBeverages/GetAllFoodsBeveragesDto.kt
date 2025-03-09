package com.example.kcalcul_compose.network.dtos.foodsBeverages


import com.squareup.moshi.Json

data class GetAllFoodsBeveragesDto(
    @Json(name = "status")
    val status: Int,
    @Json(name = "foodBeverage")
    val foodBeverage: List<FoodBeverageResponseDto>
)