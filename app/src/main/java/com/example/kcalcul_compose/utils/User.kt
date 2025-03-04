package com.example.kcalcul_compose.utils

data class User(
    var firstname : String,
    var lastname : String,
    var email : String,
    var profileUrl : String?,
    var dailyRequirements : Int = 0,
)