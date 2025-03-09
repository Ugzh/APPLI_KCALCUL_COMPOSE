package com.example.kcalcul_compose.ui.shared_component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kcalcul_compose.R
import com.example.kcalcul_compose.network.dtos.foodsBeverages.FoodBeverageResponseDto
import com.example.kcalcul_compose.network.dtos.recipes.RecipeDto
import com.example.kcalcul_compose.utils.FoodBeverageCustom
import com.example.kcalcul_compose.utils.RecipeCustom
@Composable
fun MealItemRecipeContent(
    listOfItems: List<RecipeDto>,
    valueDropDownRecipe: RecipeDto,
    valueQty: String,
    getItemSelected: (Int) -> Unit,
    onChangeText: (String) -> Unit,
    addOnClick: (RecipeDto, String) -> Unit,
    clearFieldOnClick: () -> Unit
){
    val context = LocalContext.current
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ){
        DropDownMenuSharedComponentContent(
            value = valueDropDownRecipe.name,
            listOfRecipes = listOfItems,
            getItemSelected = getItemSelected,
            modifier = Modifier.weight(0.7f)

        )
        EditTextSharedComponent(
            modifier = Modifier
                .weight(0.2f)
                .padding(bottom = 5.dp),
            placeholderStr =
            context.getString(R.string.quantity_shortcut),
            textToEdit = valueQty,
            onChangeText = onChangeText
        )
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = context.getString(R.string.add),
            modifier = Modifier.clickable {
                addOnClick(valueDropDownRecipe, valueQty)
                clearFieldOnClick()
            }
        )
    }
}

@Composable
fun MealItemFoodBeverageContent(
    listOfFoodBeverages: List<FoodBeverageResponseDto>,
    valueDropDownFood: FoodBeverageResponseDto,
    valueQty: String,
    valueUnit: String,
    getItemSelected: (Int) -> Unit,
    onChangeTextQty: (String) -> Unit,
    onChangeTextUnit: (String) -> Unit,
    addOnClick: (FoodBeverageResponseDto, String, String) -> Unit,
    clearFieldOnClick: () -> Unit
){
    val context = LocalContext.current
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ){
        DropDownMenuSharedComponentContent(
            value = valueDropDownFood.name,
            listOfFoodBeverages = listOfFoodBeverages,
            getItemSelected = getItemSelected,
            modifier = Modifier.weight(0.5f)
        )
        Spacer(modifier = Modifier.width(4.dp))
        EditTextSharedComponent(
            modifier = Modifier
                .weight(0.15f)
                .padding(bottom = 5.dp),
            placeholderStr =
            context.getString(R.string.quantity_shortcut),
            textToEdit = valueQty,
            onChangeText = onChangeTextQty
        )
        Spacer(modifier = Modifier.width(4.dp))

        EditTextSharedComponent(
            modifier = Modifier
                .weight(0.15f)
                .padding(bottom = 5.dp),
            placeholderStr =
            context.getString(R.string.unit),
            textToEdit = valueUnit,
            onChangeText = onChangeTextUnit
        )
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = context.getString(R.string.add),
            modifier = Modifier.clickable {
                addOnClick(valueDropDownFood, valueQty, valueUnit)
                clearFieldOnClick()
            }
        )
    }
}

@Composable
fun MealItemRecipeSelectedContent(
    recipeCustom: RecipeCustom,
    deleteOnClick: (RecipeCustom) -> Unit
){
    val context = LocalContext.current

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextViewSharedComponentContent(name = recipeCustom.recipe.name)
        Spacer(modifier = Modifier.width(4.dp))
        TextViewSharedComponentContent(name = recipeCustom.qty.toString())
        Spacer(modifier = Modifier.width(4.dp))
        Icon(
            imageVector = Icons.Default.DeleteOutline,
            contentDescription = context.getString(R.string.add),
            modifier = Modifier.clickable {
                deleteOnClick(recipeCustom)
            }
        )
    }
}

@Composable
fun MealItemFoodSelectedContent(
    foodBeverage: FoodBeverageCustom,
    deleteOnClick: (FoodBeverageCustom) -> Unit
){
    val context = LocalContext.current

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextViewSharedComponentContent(name = foodBeverage.nameIngredient)
        Spacer(modifier = Modifier.width(4.dp))
        TextViewSharedComponentContent(name = foodBeverage.quantity.toString())
        Spacer(modifier = Modifier.width(4.dp))
        TextViewSharedComponentContent(name = foodBeverage.unit)
        Spacer(modifier = Modifier.width(4.dp))
        Icon(
            imageVector = Icons.Default.DeleteOutline,
            contentDescription = context.getString(R.string.add),
            modifier = Modifier.clickable {
                deleteOnClick(foodBeverage)
            }
        )
    }
}