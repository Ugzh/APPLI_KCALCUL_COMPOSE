package com.example.kcalcul_compose.network.dtos.foodsBeverages


import com.squareup.moshi.Json

data class UnitDto(
    @Json(name = "name")
    val name: String
)