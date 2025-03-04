package com.example.kcalcul.network.dtos.foodsBeverages


import com.squareup.moshi.Json

data class UnitDto(
    @Json(name = "name")
    val name: String
)