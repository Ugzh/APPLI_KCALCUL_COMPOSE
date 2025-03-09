package com.example.kcalcul_compose.utils

import com.example.kcalcul_compose.network.dtos.recipes.RecipeDto

data class RecipeCustom(
    val recipe: RecipeDto,
    val qty : Int
)
