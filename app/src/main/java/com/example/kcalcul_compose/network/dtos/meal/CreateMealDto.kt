package com.example.kcalcul_compose.network.dtos.meal


import com.example.kcalcul_compose.network.dtos.foodsBeverages.FoodBeverageDto
import com.example.kcalcul_compose.network.dtos.recipes.RecipeDto
import com.squareup.moshi.Json

data class CreateMealDto(
    @Json(name = "notification")
    val notification: Boolean,
    @Json(name = "date")
    val date: String,
    @Json(name = "foodBeverages")
    val foodBeverages: List<FoodBeverageDto>,
    @Json(name = "recipes")
    val recipes: List<RecipeDto>,
    @Json(name = "idU")
    val idU: Long,
    @Json(name = "mealType")
    val mealType: String,
    @Json(name = "quantityForRecipe")
    val quantityForRecipe: List<Int>,
    @Json(name = "quantityForFoodBeverage")
    val quantityForFoodBeverage: List<Int>,
    @Json(name = "unit")
    val unit: List<String>
)