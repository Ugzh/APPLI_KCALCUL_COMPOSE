package com.example.kcalcul.network.dtos

data class User(
    var firstname : String,
    var lastname : String,
    var email : String,
    var profileUrl : String?,
    var dailyRequirements : Int = 0,
)
