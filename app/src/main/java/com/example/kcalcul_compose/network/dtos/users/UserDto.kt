package com.example.kcalcul.network.dtos.users

import com.squareup.moshi.Json

data class UserDto(
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
    @Json(name = "role")
    val role: String,
    @Json(name = "profile_url_page")
    val profileUrlPage: String?,
    @Json(name = "daily_requirements")
    val dailyRequirements: Int,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "updated_at")
    val updatedAt: String?
)
