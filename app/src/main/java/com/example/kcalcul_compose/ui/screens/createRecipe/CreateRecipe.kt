package com.example.kcalcul_compose.ui.screens.createRecipe

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kcalcul_compose.R
import com.example.kcalcul_compose.ui.shared_component.ButtonSharedComponent
import com.example.kcalcul_compose.ui.shared_component.DropDownMenuSharedComponentContent
import com.example.kcalcul_compose.ui.shared_component.EditTextSharedComponent
import com.example.kcalcul_compose.ui.shared_component.IngredientEditsTextsContent
import com.example.kcalcul_compose.ui.shared_component.IngredientSharedComponent
import com.example.kcalcul_compose.ui.shared_component.TitleSharedComponent
import com.example.kcalcul_compose.utils.FoodBeverageCustom

@Preview(showBackground = true)
@Composable
fun CreateRecipePreview(){

    CreateRecipeContent(
        listOf(
            FoodBeverageCustom("poulet", 100, 2, "kg"),
            FoodBeverageCustom("un plat plutot lo", 100, 2, "kg")
        ),
        { s, s2, s3, s4 ->  },
        backNavigation = {},
        createRecipe = {s, i ->  },
        true,
        {s ->}
    )
}

@Composable
fun CreateRecipeContent(
    listOfFoodBeverageCustoms: List<FoodBeverageCustom>,
    addIngredient: (String, String, String, String) -> Unit,
    backNavigation: () -> Unit,
    createRecipe : (String, Int) -> Unit,
    isLoading : Boolean,
    deleteItem : (String) -> Unit
){
    val dishCategories = listOf(R.string.starter, R.string.main_course, R.string.dessert)
    val context = LocalContext.current
    var name by remember {
        mutableStateOf("")
    }
    var categorySelected by remember {
        mutableIntStateOf(dishCategories.get(0))
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)) {
        TitleSharedComponent(text = context.getString(R.string.submit_a_meal))
        Spacer(modifier = Modifier.size(40.dp))
        Text(text = context.getString(R.string.name))
        EditTextSharedComponent(placeholderStr = "",
            textToEdit = name,
            onChangeText = {
                name = it
            })
        Spacer(modifier = Modifier.size(20.dp))
        Text(text = context.getString(R.string.ingredient))
        IngredientEditsTextsContent(addIngredient = addIngredient)
        Spacer(modifier = Modifier.size(20.dp))
        LazyColumn {
            items(items = listOfFoodBeverageCustoms){
                Row {
                    IngredientSharedComponent(
                        ingredient = it.nameIngredient,
                        kcal = it.kcal,
                        qty = it.quantity,
                        unit = it.unit,
                        deleteItem = deleteItem
                    )
                }
            }
        }
        Spacer(modifier = Modifier.size(20.dp))
        DropDownMenuSharedComponentContent(
            value = context.getString(categorySelected)            ,
            listOfItemStringId = dishCategories,
            labelText = context.getString(R.string.dish_category),
            getItemSelected = {
                categorySelected = it
            }
        )
        Spacer(modifier = Modifier.size(40.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 80.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ButtonSharedComponent(
                btnText = context.getString(R.string.back),
                onClickAction = { backNavigation()}
            )
            ButtonSharedComponent(
                btnText = context.getString(R.string.submit),
                onClickAction = {
                    createRecipe(name, categorySelected)
                }
            )
        }
        Spacer(modifier = Modifier.size(40.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            if (isLoading)
                CircularProgressIndicator()
        }
    }
}

@Composable
fun CreateRecipeScreen(navController: NavController, vm: CreateRecipeViewModel){
    val listOfFoodBeverages by vm.foodBeveragesListStateFlow.collectAsState()
    val context = LocalContext.current
    val isLoading by vm.progressBarLoadingStateFlow.collectAsState()

    LaunchedEffect(key1 = true) {
        vm.userMessageSharedFlow.collect{
            Toast.makeText(context, context.getString(it), Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(key1 = true) {
        vm.recipeCreatedSharedFlow.collect{
            if(it)
                navController.popBackStack()
        }
    }

    CreateRecipeContent(
        listOfFoodBeverages,
        addIngredient = { ingredient, kcal, qty, unit ->
            vm.addIngredient(ingredient, kcal, qty, unit)
        },
        backNavigation = { navController.popBackStack() },
        createRecipe = { name, dishCategory ->
            vm.createRecipe(name, context.getString(dishCategory))
        },
        isLoading,
        deleteItem = { vm.removeIngredient(it) }
    )

}