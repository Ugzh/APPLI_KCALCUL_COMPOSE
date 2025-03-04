package com.example.kcalcul.network.dtos.users


import com.squareup.moshi.Json

data class UpdateUserDto(
    @Json(name = "firstname")
    val firstname: String?,
    @Json(name = "lastname")
    val lastname: String?,
    @Json(name = "email")
    val email: String?,
    @Json(name = "daily_requirements")
    val dailyRequirements: Int?
)