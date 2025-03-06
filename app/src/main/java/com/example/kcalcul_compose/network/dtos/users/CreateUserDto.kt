package com.example.kcalcul_compose.network.dtos.users


import com.squareup.moshi.Json

data class CreateUserDto(
    @Json(name = "id")
    val id: Long,
    @Json(name = "firstname")
    val firstname: String,
    @Json(name = "lastname")
    val lastname: String,
    @Json(name = "email")
    val email: String,
    @Json(name = "password")
    val password: String,
    @Json(name = "profile_url_image")
    val profileUrlImage: String?,
    @Json(name = "daily_requirements")
    val dailyRequirements: Int
)